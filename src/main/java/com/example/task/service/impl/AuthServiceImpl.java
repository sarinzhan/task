package com.example.task.service.impl;


import com.example.task.dto.request.AuthenticateRequestDto;
import com.example.task.dto.response.TokenResponseDto;
import com.example.task.entity.User;
import com.example.task.exception.BaseLogicException;
import com.example.task.jwt.JwtTokenHandler;
import com.example.task.service.AuthService;
import com.example.task.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserService userService;
    private final JwtTokenHandler jwtTokenHandler;
    private final PasswordEncoder passwordEncoder;

    @Override
    public TokenResponseDto authenticateUser(AuthenticateRequestDto cred) {
        TokenResponseDto tokenResponse = new TokenResponseDto();
        User user = userService.findByUsername(cred.getUsername());
        if(passwordEncoder.matches(cred.getPassword(), user.getPassword())){
            Authentication auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            tokenResponse.setToken(jwtTokenHandler.generateToken(auth));
            tokenResponse.setRole(user.getRole().name());
            return tokenResponse;
        }else{
            throw new BaseLogicException("Ошибка авторизации");
        }

    }
}
