package com.example.task.mapper.request;

import com.example.task.dto.request.ApplicationUpdateRequestDto;
import com.example.task.entity.Application;
import com.example.task.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;
import java.util.Objects;

@Mapper(componentModel = "spring",imports = {LocalDateTime.class})
public interface ApplicationUpdateRequestMapper {

    @Mappings({
            @Mapping(target = "assignedTo", expression = "java(setAssignedTo(dto))"),
            @Mapping(target = "createdAt", expression = "java(LocalDateTime.now())"),
            @Mapping(target = "assignedAt", expression = "java(setAssignedAt(dto))"),
            @Mapping(target = "createdBy", expression = "java(getAuthUser())"),
    })
    Application dtoToEntity(ApplicationUpdateRequestDto dto);

    default User getAuthUser(){
        return (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }

    default User setAssignedTo(ApplicationUpdateRequestDto dto){
        if(Objects.nonNull(dto.getAssignedUserId())){
            User user = new User();
            user.setId(dto.getAssignedUserId());
            return user;
        }
        return null;
    }


    default LocalDateTime setAssignedAt(ApplicationUpdateRequestDto dto){
        if(Objects.nonNull(dto.getAssignedUserId())){
            return java.time.LocalDateTime.now();
        }else{
            return null;
        }
    }
}
