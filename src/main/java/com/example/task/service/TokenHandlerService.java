package com.example.task.service;

import com.example.task.entity.TokenHandler;
import com.example.task.entity.User;

public interface TokenHandlerService {
    void save(TokenHandler tokenHandler);

    TokenHandler getByUserId(Long userId);

    String generateToken(User user);

    boolean verifyToken(String token);

    TokenHandler getToken(String token);
}
