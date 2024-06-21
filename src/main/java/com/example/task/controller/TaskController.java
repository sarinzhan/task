package com.example.task.controller;

import com.example.task.dto.CommonResponseDto;
import com.example.task.dto.request.CreateTaskRequestDto;
import com.example.task.dto.response.TaskResponseDto;
import com.example.task.mapper.CreateTaskRequestMapper;
import com.example.task.mapper.TaskResponseMapper;
import com.example.task.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/task")
@RestController
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    private final CreateTaskRequestMapper createTaskRequestMapper;
    private final TaskResponseMapper taskResponseMapper;

    @PostMapping("/create")
    public CommonResponseDto<Long> create(
            @RequestBody CreateTaskRequestDto requestDto
    ){
        return new CommonResponseDto<Long>()
                .setOk()
                .setData(
                        taskService.create(
                                createTaskRequestMapper.dtoToEntity(requestDto)).getId()
                );
    }

    @GetMapping("/get-all")
    public CommonResponseDto<List<TaskResponseDto>> getAll(){
        return new CommonResponseDto<List<TaskResponseDto>>()
                .setOk()
                .setData(
                        taskResponseMapper.listEntityToDto(
                                taskService.getAll()
                        )
                );
    }


}
