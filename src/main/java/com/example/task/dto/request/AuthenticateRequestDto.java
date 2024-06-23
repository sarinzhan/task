package com.example.task.dto.request;

import lombok.Data;

@Data
public class AuthenticateRequestDto {
    private String username;
    private String password;
}
