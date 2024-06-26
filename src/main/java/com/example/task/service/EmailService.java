package com.example.task.service;

public interface EmailService {
    void sendMessage(String email, String subject, String messageText);
}
