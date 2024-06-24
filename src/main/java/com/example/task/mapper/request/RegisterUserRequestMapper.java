package com.example.task.mapper.request;

import com.example.task.dto.request.RegisterUserRequestDto;
import com.example.task.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring",imports = {LocalDateTime.class})
public interface RegisterUserRequestMapper {

    @Mappings({
            @Mapping(target = "createdAt", expression = "java(LocalDateTime.now())")
    })
    User dtoToEntity(RegisterUserRequestDto dto);
}
