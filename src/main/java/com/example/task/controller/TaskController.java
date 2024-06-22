package com.example.task.controller;

import com.example.task.dto.CommonResponseDto;
import com.example.task.dto.request.CreateApplicationRequestDto;
import com.example.task.dto.response.TaskResponseDto;
import com.example.task.mapper.CreateApplicationRequestMapper;
import com.example.task.mapper.TaskResponseMapper;
import com.example.task.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/task")
@RestController
@RequiredArgsConstructor
public class TaskController {
    private final ApplicationService applicationSerivce;

    private final CreateApplicationRequestMapper createTaskRequestMapper;
    private final TaskResponseMapper taskResponseMapper;

    @PostMapping("/create")
    public CommonResponseDto<Long> create(
            @RequestBody CreateApplicationRequestDto requestDto
    ){
        return new CommonResponseDto<Long>()
                .setOk()
                .setData(
                        applicationSerivce.create(
                                createTaskRequestMapper.dtoToEntity(requestDto)).getId()
                );
    }

    @GetMapping("/get-all")
    public CommonResponseDto<List<TaskResponseDto>> getAll(){
        return new CommonResponseDto<List<TaskResponseDto>>()
                .setOk()
                .setData(
                        taskResponseMapper.listEntityToDto(
                                applicationSerivce.getAll()
                        )
                );
    }


}
