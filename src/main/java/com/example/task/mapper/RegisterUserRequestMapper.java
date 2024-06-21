package com.example.task.mapper;

import com.example.task.dto.request.RegisterUserRequestDto;
import com.example.task.entity.Users;

public class RegisterUserRequestMapper {
    public static Users dtoToEntity(RegisterUserRequestDto registerUserRequestDto){
        Users users = new Users();
        users.setFirstName(registerUserRequestDto.getFirstName());
        users.setLastName(registerUserRequestDto.getLastName());
        users.setLogin(registerUserRequestDto.getLogin());
        users.setPassword(registerUserRequestDto.getPassword());
        return users;
    }
}
