package com.example.task.dto.response;

import lombok.Data;
import org.springframework.core.io.Resource;

import java.time.LocalDateTime;

@Data
public class AttachmentResponseDto {
    private Long id;
    private Long applicationId;
    private String presignedFileUrl;
    private String fileName;
    private Long uploadedUserId;
    private LocalDateTime uploadedAt;
}
