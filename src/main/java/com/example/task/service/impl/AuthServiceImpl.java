package com.example.task.service.impl;


import com.example.task.dto.request.LoginRequestDto;
import com.example.task.dto.response.TokenResponseDto;
import com.example.task.exception.BaseLogicException;
import com.example.task.jwt.JwtTokenHandler;
import com.example.task.service.AuthService;
import com.example.task.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserService userService;
    private final JwtTokenHandler jwtTokenHandler;
    private final PasswordEncoder passwordEncoder;

    @Override
    public TokenResponseDto authenticateUser(LoginRequestDto cred) {
        TokenResponseDto tokenResponse = new TokenResponseDto();
        UserDetails userDetails = userService.loadUserByUsername(cred.getUsername());
        if(passwordEncoder.matches(cred.getPassword(), userDetails.getPassword())){
            Authentication auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            userDetails.getAuthorities().forEach(x -> tokenResponse.setRole(x.getAuthority()));
            tokenResponse.setToken(jwtTokenHandler.generateToken(auth));
            return tokenResponse;
        }else{
            throw new BaseLogicException("Ошибка авторизации");
        }

    }
}
