package com.example.task.dto.request;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class CreateApplicationRequestDto {
    private Long userId;
    private String title;
    private String description;
    private LocalDateTime dueDate;
}
