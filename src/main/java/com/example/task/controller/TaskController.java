package com.example.task.controller;

import com.example.task.dto.CommonResponseDto;
import com.example.task.dto.request.CreateApplicationRequestDto;
import com.example.task.dto.response.ApplicationResponseDto;
import com.example.task.mapper.CreateApplicationRequestMapper;
import com.example.task.mapper.ApplicationResponseMapper;
import com.example.task.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/task")
@RestController
@RequiredArgsConstructor
public class TaskController {
    private final ApplicationService applicationService;

    private final CreateApplicationRequestMapper createTaskRequestMapper;
    private final ApplicationResponseMapper applicationResponseMapper;

    @PostMapping("/create")
    public CommonResponseDto<Long> create(
            @RequestBody CreateApplicationRequestDto requestDto
    ){
        return new CommonResponseDto<Long>()
                .setOk()
                .setData(
                        applicationService.create(
                                createTaskRequestMapper.dtoToEntity(requestDto)).getId()
                );
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
