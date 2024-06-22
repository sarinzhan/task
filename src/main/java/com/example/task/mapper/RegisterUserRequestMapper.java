package com.example.task.mapper;

import com.example.task.dto.request.RegisterUserRequestDto;
import com.example.task.entity.User;

public class RegisterUserRequestMapper {
    public static User dtoToEntity(RegisterUserRequestDto registerUserRequestDto){
        User user = new User();
        user.setFirstName(registerUserRequestDto.getFirstName());
        user.setLastName(registerUserRequestDto.getLastName());
        user.setLogin(registerUserRequestDto.getLogin());
        user.setPassword(registerUserRequestDto.getPassword());
        return user;
    }
}
