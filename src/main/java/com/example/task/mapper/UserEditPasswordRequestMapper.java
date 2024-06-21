package com.example.task.mapper;

import com.example.task.dto.request.UserEditPasswordRequestDto;
import com.example.task.entity.Users;

public class UserEditPasswordRequestMapper {

    public static Users dtoToEntity(UserEditPasswordRequestDto dto){
        Users users = new Users();
        users.setLogin(dto.getLogin());
        users.setPassword(dto.getOldPassword());
        return users;
    }
}
