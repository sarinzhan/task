package com.example.task.dto.request;

import com.example.task.entity.enums.ApplicationPriority;
import com.example.task.entity.enums.ApplicationStatus;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class CreateApplicationRequestDto {
    private Long createdUserId;
    private String title;
    private String description;
    private ApplicationStatus status;
    private ApplicationPriority priority;
    private LocalDateTime dueDate;
    private Long assignedUserId;
}
