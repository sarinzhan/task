package com.example.task.dto.request;

import com.example.task.entity.enums.UserRole;
import jakarta.persistence.Column;
import lombok.Data;

@Data
public class RegisterUserRequestDto {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private UserRole role;
}
