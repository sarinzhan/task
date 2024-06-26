package com.example.task.service.impl;

import com.example.task.entity.TokenHandler;
import com.example.task.entity.User;
import com.example.task.exception.BaseLogicException;
import com.example.task.repository.TokenHandlerRepository;
import com.example.task.service.TokenHandlerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Base64;

@Service
@RequiredArgsConstructor
public class TokenHandlerServiceImpl implements TokenHandlerService {
    private final TokenHandlerRepository tokenHandlerRepository;

    @Override
    public void save(TokenHandler tokenHandler) {
        try{
            tokenHandlerRepository.save(tokenHandler);
        }catch (Exception ex){
            throw new BaseLogicException("Не удалось сохранить токен");
        }
    }

    @Override
    public TokenHandler getByUserId(Long userId) {
        try{
            return tokenHandlerRepository.getByUserId(userId);
        }catch (Exception ex){
            throw new BaseLogicException("Не удалось найти токен");
        }
    }

    @Override
    public String generateToken(User user) {
        SecureRandom secureRandom = new SecureRandom();
        byte[] tokenBytes = new byte[24];
        secureRandom.nextBytes(tokenBytes);
        String token = Base64.getUrlEncoder().withoutPadding().encodeToString(tokenBytes);
        TokenHandler tokenHandler = new TokenHandler();
        tokenHandler.setUser(user);
        tokenHandler.setExpireAt(LocalDateTime.now().plusMinutes(5));
        tokenHandler.setToken(token);
        tokenHandler.setIsApplied(false);
        try{
            tokenHandlerRepository.save(tokenHandler);
            return token;
        }catch (Exception ex){
            throw new BaseLogicException("Не удалось создать токен");
        }
    }

    @Override
    public boolean verifyToken(String token) {
        tokenHandlerRepository.getByToken(token)
                .orElseThrow(() -> new BaseLogicException("Не удалось найти токен"));
        return true;
    }

    @Override
    public TokenHandler getToken(String token) {
        TokenHandler tokenHandler = tokenHandlerRepository.getByToken(token)
                .orElseThrow(() -> new BaseLogicException("Не удалось найти токен"));
        if(tokenHandler.getIsApplied()){
            throw new BaseLogicException("Токен уже использован");
        }
        tokenHandler.setIsApplied(true);
        tokenHandlerRepository.save(tokenHandler);
        return tokenHandler;

    }
}
