package com.example.task.mapper;

import com.example.task.dto.request.CreateApplicationRequestDto;
import com.example.task.entity.Application;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring",imports = {LocalDateTime.class})
public interface CreateApplicationRequestMapper {

    @Mappings({
            @Mapping(target = "user.id", source = "userId"),
            @Mapping(target = "status.id", constant = "1L"),
            @Mapping(target = "createdAt", expression = "java(LocalDateTime.now())")
    })
    Application dtoToEntity(CreateApplicationRequestDto dto);
}
