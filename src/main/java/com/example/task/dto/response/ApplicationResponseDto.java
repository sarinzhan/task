package com.example.task.dto.response;

import com.example.task.entity.enums.ApplicationPriority;
import com.example.task.entity.enums.ApplicationStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApplicationResponseDto {
    private String title;
    private String description;
    private ApplicationStatus status;
    private ApplicationPriority priority;
    private LocalDateTime dueDate;
    private Long createdUserId;
    private LocalDateTime createdAt;
    private Long assignedUserId;
    private LocalDateTime assignedAt;
}
