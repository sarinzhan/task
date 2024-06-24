package com.example.task.controller;


import com.example.task.dto.CommonResponseDto;
import com.example.task.dto.request.AttachmentCreateRequestDto;
import com.example.task.dto.response.AttachmentResponseDto;
import com.example.task.entity.Application;
import com.example.task.mapper.request.AttachmentCreateRequestMapper;
import com.example.task.mapper.response.AttachmentResponseMapper;
import com.example.task.service.ApplicationService;
import com.example.task.service.AttachmentService;
import com.example.task.service.S3Service;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/attachment")
@RequiredArgsConstructor
@Tag(name = "Attachment API")
public class AttachmentController {
    private final AttachmentService attachmentService;
    private final ApplicationService applicationService;
    private final S3Service s3Service;

    private final AttachmentCreateRequestMapper attachmentCreateRequestMapper;
    private final AttachmentResponseMapper attachmentResponseMapper;

    @PostMapping("/create")
    public CommonResponseDto<Void> create(
            @ModelAttribute AttachmentCreateRequestDto requestDto
    ){
        attachmentService.add(
                attachmentCreateRequestMapper.dtoToEntity(requestDto)
        );
        MultipartFile file = requestDto.getFile();
        s3Service.saveFile(file.getOriginalFilename(), file);
        return new CommonResponseDto<Void>()
                .setOk();
    }

    @GetMapping("/get-all-by-application-id")
    public CommonResponseDto<List<AttachmentResponseDto>> getAllByApplicationId(
            @RequestParam Long applicationId
    ){
        Application application = applicationService.getById(applicationId);
        return new CommonResponseDto<List<AttachmentResponseDto>>()
                .setOk()
                .setData(
                        attachmentResponseMapper.listEntityToDto(
                                attachmentService.getAllByApplication(application)
                        )
                );
    }

    @PostMapping("/remove-by-id")
    public CommonResponseDto<Void> removeById(
            @RequestParam Long attachmentId
    ){
        attachmentService.removeById(attachmentId);
        return new CommonResponseDto<Void>()
                .setOk();
    }

    @PostMapping("/remove-all")
    public CommonResponseDto<Void> removeAll(){
        attachmentService.removeAll();
        return new CommonResponseDto<Void>()
                .setOk();
    }


}
