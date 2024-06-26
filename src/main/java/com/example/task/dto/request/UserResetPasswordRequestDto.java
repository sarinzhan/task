package com.example.task.dto.request;

import lombok.Data;

@Data
public class UserResetPasswordRequestDto {
    private String token;
    private String password;
}
