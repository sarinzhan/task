package com.example.task.dto.request;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class RegisterUserRequestDto {
    private String login;
    private String password;
    private String firstName;
    private String lastName;
}
