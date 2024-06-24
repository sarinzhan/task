package com.example.task.dto.request;

import com.example.task.entity.Application;
import com.example.task.entity.User;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
public class AttachmentCreateRequestDto {

    private Long applicationId;

    private MultipartFile file;
}
