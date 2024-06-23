package com.example.task.dto.request;

import lombok.Data;

@Data
public class LoginRequestDto {
    private String password;
    private String username;
}
