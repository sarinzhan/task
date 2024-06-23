package com.example.task.mapper;

import com.example.task.dto.request.AuthenticateRequestDto;
import com.example.task.entity.User;

public class AuthenticateRequestMapper {
    public static User dtoToEntity(AuthenticateRequestDto authenticateRequestDto){
        User user = new User();
        user.setUsername(authenticateRequestDto.getLogin());
        user.setPassword(authenticateRequestDto.getPassword());
        return user;
    }
}
