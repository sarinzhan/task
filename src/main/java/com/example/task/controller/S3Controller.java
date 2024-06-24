package com.example.task.controller;

import com.example.task.service.S3Service;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/s3")
@RequiredArgsConstructor
@RestController
@Tag(name = "S3 API")
public class S3Controller {
    private final S3Service s3Service;

// -----****TEST ENDPOINTS
    @PostMapping(value = "/upload")
    public String uploadFile(
            @RequestParam("file") MultipartFile file
    ){
        s3Service.saveFile(file.getOriginalFilename(), file);
        return "OK";
    }

    @GetMapping(value = "/download")
    public ResponseEntity<Resource> downloadFile(
            @RequestParam String file
    ){
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(new InputStreamResource(s3Service.getFile(file).getObjectContent()));
    }
}
