package com.example.task.mapper;

import com.example.task.dto.request.AuthenticateRequestDto;
import com.example.task.entity.Users;

public class AuthenticateRequestMapper {
    public static Users dtoToEntity(AuthenticateRequestDto authenticateRequestDto){
        Users users = new Users();
        users.setLogin(authenticateRequestDto.getLogin());
        users.setPassword(authenticateRequestDto.getPassword());
        return users;
    }
}
