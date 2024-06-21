package com.example.task.mapper;

import com.example.task.dto.response.UserResponseDto;
import com.example.task.entity.Users;

import java.util.List;

public class UserRequestMapper {
    public static UserResponseDto entityToDto(Users users){
        UserResponseDto dto = new UserResponseDto();
        dto.setFirstName(users.getFirstName());
        dto.setLogin(users.getLogin());
        return dto;
    }

    public static List<UserResponseDto> listEntityToDto(List<Users> usersList){
        return usersList.stream()
                .map(UserRequestMapper::entityToDto)
                .toList();
    }
}
