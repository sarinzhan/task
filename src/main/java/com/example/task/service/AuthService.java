package com.example.task.service;

import com.example.task.dto.request.AuthenticateRequestDto;
import com.example.task.dto.response.TokenResponseDto;

public interface AuthService {
    TokenResponseDto authenticateUser(AuthenticateRequestDto cred);
}
