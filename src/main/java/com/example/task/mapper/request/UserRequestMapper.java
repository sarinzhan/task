package com.example.task.mapper.request;

import com.example.task.dto.response.UserResponseDto;
import com.example.task.entity.User;

import java.util.List;

public class UserRequestMapper {
    public static UserResponseDto entityToDto(User user){
        UserResponseDto dto = new UserResponseDto();
        dto.setFirstName(user.getFirstName());
        dto.setLogin(user.getUsername());
        return dto;
    }

    public static List<UserResponseDto> listEntityToDto(List<User> userList){
        return userList.stream()
                .map(UserRequestMapper::entityToDto)
                .toList();
    }
}
