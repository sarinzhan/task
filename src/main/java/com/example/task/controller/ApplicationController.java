package com.example.task.controller;

import com.example.task.dto.CommonResponseDto;
import com.example.task.dto.request.ApplicationRegisterRequestDto;
import com.example.task.dto.request.ApplicationUpdateRequestDto;
import com.example.task.dto.response.ApplicationResponseDto;
import com.example.task.entity.User;
import com.example.task.entity.enums.ApplicationPriority;
import com.example.task.entity.enums.ApplicationStatus;
import com.example.task.mapper.request.ApplicationRegisterRequestMapper;
import com.example.task.mapper.request.ApplicationUpdateRequestMapper;
import com.example.task.mapper.response.ApplicationResponseMapper;
import com.example.task.service.ApplicationService;
import com.example.task.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/application")
@RestController
@RequiredArgsConstructor
@Tag(name = "Application API")
public class ApplicationController {
    private final ApplicationService applicationService;
    private final UserService userService;

    private final ApplicationRegisterRequestMapper createTaskRequestMapper;
    private final ApplicationResponseMapper applicationResponseMapper;
    private final ApplicationUpdateRequestMapper updateApplicationRequestMapper;

    @PostMapping("/create")
    public CommonResponseDto<Long> create(
            @RequestBody ApplicationRegisterRequestDto requestDto
    ){
        return new CommonResponseDto<Long>()
                .setOk()
                .setData(
                        applicationService.create(
                                createTaskRequestMapper.dtoToEntity(requestDto)).getId()
                );
    }


    @GetMapping("/get-by-id")
    public CommonResponseDto<ApplicationResponseDto> getById(
            @RequestParam Long applicationId
    ){
        return new CommonResponseDto<ApplicationResponseDto>()
                .setOk()
                .setData(
                        applicationResponseMapper.entityToDto(
                                applicationService.getById(applicationId)
                        )
                );
    }

    @GetMapping("/get-all-by-status")
    public CommonResponseDto<List<ApplicationResponseDto>> getByStatus(
            @RequestParam ApplicationStatus applicationStatus
    ){
        return new CommonResponseDto<List<ApplicationResponseDto>>()
                .setOk()
                .setData(
                        applicationResponseMapper.listEntityToDto(
                                applicationService.getAllByStatus(applicationStatus)
                        )
                );
    }

    @GetMapping("/get-all-by-priority")
    public CommonResponseDto<List<ApplicationResponseDto>> getByPriority(
            @RequestParam ApplicationPriority applicationPriority
    ){
        return new CommonResponseDto<List<ApplicationResponseDto>>()
                .setOk()
                .setData(
                        applicationResponseMapper.listEntityToDto(
                                applicationService.getAllByPriority(applicationPriority)
                        )
                );
    }

    @GetMapping("/get-all-by-created-user-id")
    public CommonResponseDto<List<ApplicationResponseDto>> getAllByCreatedUserId(
            @RequestParam Long userId
    ){
        User user = userService.findById(userId);
        return new CommonResponseDto<List<ApplicationResponseDto>>()
                .setOk()
                .setData(
                        applicationResponseMapper.listEntityToDto(
                                applicationService.getAllByCreatedUser(user)
                        )
                );
    }

    @GetMapping("/get-all-by-assigned-user-id")
    public CommonResponseDto<List<ApplicationResponseDto>> getAllByAssignedUserId(
            @RequestParam Long userId
    ){
        User user = userService.findById(userId);
        return new CommonResponseDto<List<ApplicationResponseDto>>()
                .setOk()
                .setData(
                        applicationResponseMapper.listEntityToDto(
                                applicationService.getAllByAssignedUser(user)
                        )
                );
    }

    @PostMapping("/update")
    public CommonResponseDto<Void> update(
            @RequestBody ApplicationUpdateRequestDto requestDto
    ){
        applicationService.update(
                updateApplicationRequestMapper.dtoToEntity(requestDto)
        );
        return new CommonResponseDto<Void>()
                .setOk();
    }

    @GetMapping("/get-all")
    public CommonResponseDto<List<ApplicationResponseDto>> getAll(){
        return new CommonResponseDto<List<ApplicationResponseDto>>()
                .setOk()
                .setData(
                        applicationResponseMapper.listEntityToDto(
                                applicationService.getAll()
                        )
                );
    }


}
