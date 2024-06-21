package com.example.task.dto.request;

import lombok.Data;

@Data
public class UserEditPasswordRequestDto {
    private String login;
    private String oldPassword;
    private String newPassword;
}
