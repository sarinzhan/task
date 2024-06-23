package com.example.task.controller;

import com.example.task.dto.CommonResponseDto;
import com.example.task.dto.request.AuthenticateRequestDto;
import com.example.task.dto.request.RegisterUserRequestDto;
import com.example.task.dto.request.UserEditPasswordRequestDto;
import com.example.task.dto.response.UserResponseDto;
import com.example.task.mapper.AuthenticateRequestMapper;
import com.example.task.mapper.RegisterUserRequestMapper;
import com.example.task.mapper.UserEditPasswordRequestMapper;
import com.example.task.mapper.UserRequestMapper;
import com.example.task.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/user")
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    private final RegisterUserRequestMapper registerUserRequestMapper;

    @PostMapping("/register")
    public CommonResponseDto<Long> createUser(
            @RequestBody RegisterUserRequestDto registerUserRequestDto
    ){
        userService.create(
                registerUserRequestMapper.dtoToEntity(registerUserRequestDto)
        );
        return new CommonResponseDto<Long>()
                .setOk();
    }

    @PostMapping("/auth")
    public CommonResponseDto<Void> authUser(
            @RequestBody AuthenticateRequestDto authenticateRequestDto
    ){
        userService.auth(
                AuthenticateRequestMapper.dtoToEntity(authenticateRequestDto)
        );
        return new CommonResponseDto<Void>()
                .setOk();
    }

    @PostMapping("/edit-password")
    public CommonResponseDto<Void> editPassword(
            @RequestBody UserEditPasswordRequestDto userEditPasswordRequestDto
    ){
        userService.editPassword(
                UserEditPasswordRequestMapper.dtoToEntity(userEditPasswordRequestDto),
                userEditPasswordRequestDto.getNewPassword()
        );
        return new CommonResponseDto<Void>()
                .setOk();
    }

    @GetMapping("/get-all")
    public CommonResponseDto<List<UserResponseDto>> getAll(){
        return new CommonResponseDto<List<UserResponseDto>>()
                .setOk()
                .setData(
                        UserRequestMapper.listEntityToDto(
                                userService.getAll())
                );
    }
}
