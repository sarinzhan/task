package com.example.task.mapper;

import com.example.task.dto.request.CreateTaskRequestDto;
import com.example.task.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring",imports = {LocalDateTime.class})
public interface CreateTaskRequestMapper {

    @Mappings({
            @Mapping(target = "user.id", source = "userId"),
            @Mapping(target = "status.id", constant = "1L"),
            @Mapping(target = "createdAt", expression = "java(LocalDateTime.now())")
    })
    Task dtoToEntity(CreateTaskRequestDto dto);
}
