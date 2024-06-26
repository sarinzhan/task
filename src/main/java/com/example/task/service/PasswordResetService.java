package com.example.task.service;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

public interface PasswordResetService {

    void sendEmailToResetPassword(String username);

    void resetPassword(String token, String password);
}
