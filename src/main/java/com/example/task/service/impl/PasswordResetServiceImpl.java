package com.example.task.service.impl;

import com.example.task.entity.TokenHandler;
import com.example.task.entity.User;
import com.example.task.exception.BaseLogicException;
import com.example.task.service.EmailService;
import com.example.task.service.PasswordResetService;
import com.example.task.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PasswordResetServiceImpl implements PasswordResetService {
    private final UserService userService;
    private final EmailService emailService;
    private final TokenHandlerServiceImpl tokenHandlerService;
    @Override
    public void sendEmailToResetPassword(String username) {
        User user = userService.findByUsername(username);
        String token = tokenHandlerService.generateToken(user);

        String resetUrl = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/user")
                .path("/reset-password")
                .queryParam("token", token)
                .toUriString();

        emailService.sendMessage(user.getEmail(), "Password Reset Request", "To reset your password, click the link below:\n" + resetUrl);
    }

    @Override
    public void resetPassword(String token, String newPassword) {
        TokenHandler tokenHandler = tokenHandlerService.getToken(token);
        User user = tokenHandler.getUser();
        if(tokenHandler.getExpireAt().isAfter(LocalDateTime.now())){
            user.setPassword(newPassword);
            userService.update(user);
        }else {
            throw new BaseLogicException("Токен просрочен");
        }
    }
}
