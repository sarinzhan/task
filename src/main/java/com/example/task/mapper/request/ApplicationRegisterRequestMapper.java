package com.example.task.mapper.request;

import com.example.task.dto.request.ApplicationRegisterRequestDto;
import com.example.task.entity.Application;
import com.example.task.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;
import java.util.Objects;

@Mapper(componentModel = "spring",imports = {LocalDateTime.class})
public interface ApplicationRegisterRequestMapper {

    @Mappings({
            @Mapping(target = "createdBy", expression = "java(getAuthUser())"),
            @Mapping(target = "createdAt", expression = "java(LocalDateTime.now())"),
            @Mapping(target = "assignedTo", expression = "java(setAssignedTo(dto))"),
            @Mapping(target = "assignedAt", expression = "java(setAssignedAt(dto))")
    })
    Application dtoToEntity(ApplicationRegisterRequestDto dto);

    default User getAuthUser(){
        return (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }

    default User setAssignedTo(ApplicationRegisterRequestDto dto){
        if(Objects.nonNull(dto.getAssignedUserId())){
            User user = new User();
            user.setId(dto.getAssignedUserId());
            return user;
        }
        return null;
    }
    default LocalDateTime setAssignedAt(ApplicationRegisterRequestDto dto){
        if(Objects.nonNull(dto.getAssignedUserId())){
            return LocalDateTime.now();
        }else{
            return null;
        }
    }
}
