package com.example.task.service;

import com.example.task.dto.response.AttachmentResponseDto;
import com.example.task.entity.Application;
import com.example.task.entity.Attachment;

import java.util.List;

public interface AttachmentService {
    void add(Attachment attachment);

    Attachment findById(Long attachmentId);

    List<Attachment> getAllByApplication(Application application);

    void removeById(Long attachmentId);

    void removeAll();
}
