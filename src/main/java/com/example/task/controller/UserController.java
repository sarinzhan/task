package com.example.task.controller;

import com.example.task.dto.CommonResponseDto;
import com.example.task.dto.request.AuthenticateRequestDto;
import com.example.task.dto.request.RegisterUserRequestDto;
import com.example.task.dto.request.UserResetPasswordRequestDto;
import com.example.task.dto.response.TokenResponseDto;
import com.example.task.dto.response.UserResponseDto;
import com.example.task.mapper.request.RegisterUserRequestMapper;
import com.example.task.mapper.request.UserRequestMapper;
import com.example.task.service.AuthService;
import com.example.task.service.PasswordResetService;
import com.example.task.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/user")
@RestController
@RequiredArgsConstructor
@Tag(name = "User API")
public class UserController {
    private final UserService userService;
    private final AuthService authService;
    private final PasswordResetService passwordResetService;

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
    public CommonResponseDto<TokenResponseDto> authUser(
            @RequestBody AuthenticateRequestDto authenticateRequestDto
    ){
        return new CommonResponseDto<TokenResponseDto>()
                .setOk()
                .setData(
                        authService.authenticateUser(authenticateRequestDto)
                );
    }

    @GetMapping("/get-email-message-to-reset-password")
    public CommonResponseDto<Void> resetPassword(
            @RequestParam String username
    ){
        passwordResetService.sendEmailToResetPassword(username);
        return new CommonResponseDto<Void>()
                .setOk();
    }

    @PostMapping("/reset-password")
    public CommonResponseDto<Void> editPassword(
            @RequestParam String token,
            @RequestParam String password
    ){
        passwordResetService.resetPassword(token,password);
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
