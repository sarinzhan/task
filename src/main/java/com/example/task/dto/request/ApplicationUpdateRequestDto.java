package com.example.task.dto.request;

import com.example.task.entity.enums.ApplicationPriority;
import com.example.task.entity.enums.ApplicationStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApplicationUpdateRequestDto {
    private Long id;

    private String title;

    private String description;

    private ApplicationStatus status;

    private ApplicationPriority priority;

    private LocalDateTime dueDate;

    private LocalDateTime createdAt;

    private LocalDateTime assignedAt;

    private Long assignedUserId;
}
