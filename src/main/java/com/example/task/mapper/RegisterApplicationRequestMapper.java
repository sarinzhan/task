package com.example.task.mapper;

import com.example.task.dto.request.CreateApplicationRequestDto;
import com.example.task.entity.Application;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.time.LocalDateTime;
import java.util.Objects;

@Mapper(componentModel = "spring",imports = {LocalDateTime.class})
public interface RegisterApplicationRequestMapper {

    @Mappings({
            @Mapping(target = "createdBy.id", source = "createdUserId"),
            @Mapping(target = "createdAt", expression = "java(LocalDateTime.now())"),
            @Mapping(target = "assignedTo.id", source = "assignedUserId"),
            @Mapping(target = "assignedAt", expression = "java(setAssignedAt(dto))")
    })
    Application dtoToEntity(CreateApplicationRequestDto dto);

    default LocalDateTime setAssignedAt(CreateApplicationRequestDto dto){
        if(Objects.nonNull(dto.getAssignedUserId())){
            return LocalDateTime.now();
        }else{
            return null;
        }
    }
}
