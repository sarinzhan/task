package com.example.task.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskResponseDto {
    private String userFirstName;
    private Long userId;
    private String title;
    private String description;
    private String status;
    private LocalDateTime dueDate;
    private LocalDateTime createdAt;
}
