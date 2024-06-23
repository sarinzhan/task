package com.example.task.dto.response;

import com.example.task.entity.enums.UserRole;
import lombok.Data;

@Data
public class TokenResponseDto {
    private String token;
    private String role;
}
