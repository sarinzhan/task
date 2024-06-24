package com.example.task.service;

import com.amazonaws.services.s3.model.S3Object;
import org.springframework.web.multipart.MultipartFile;

public interface S3Service {

    void saveFile(String keyName, MultipartFile file);

    S3Object getFile(String keyName);

    String getPresignedUrl(String keyName);

    void removeByKey(String key);
    void removeAll();
}
