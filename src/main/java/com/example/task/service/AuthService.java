package com.example.task.service;

import com.example.task.dto.request.LoginRequestDto;
import com.example.task.dto.response.TokenResponseDto;

public interface AuthService {
    TokenResponseDto authenticateUser(LoginRequestDto cred);
}
