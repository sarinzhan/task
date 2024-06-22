package com.example.task.mapper;

import com.example.task.dto.request.UserEditPasswordRequestDto;
import com.example.task.entity.User;

public class UserEditPasswordRequestMapper {

    public static User dtoToEntity(UserEditPasswordRequestDto dto){
        User user = new User();
        user.setLogin(dto.getLogin());
        user.setPassword(dto.getOldPassword());
        return user;
    }
}
