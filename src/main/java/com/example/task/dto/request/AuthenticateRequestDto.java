package com.example.task.dto.request;

import lombok.Data;

@Data
public class AuthenticateRequestDto {
    private String login;
    private String password;
}
