package com.example.task.mapper.request;

import com.example.task.dto.request.AttachmentCreateRequestDto;
import com.example.task.entity.Attachment;
import com.example.task.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring",imports = {LocalDateTime.class})
public interface AttachmentCreateRequestMapper {

    @Mappings({
            @Mapping(target = "filePath", expression = "java(sourceDto.getFile().getOriginalFilename())"),
            @Mapping(target = "uploadedAt", expression = "java(LocalDateTime.now())"),
            @Mapping(target = "uploadedBy", expression = "java(getAuthUser())"),
            @Mapping(target = "application.id", source = "applicationId")
    })
    Attachment dtoToEntity(AttachmentCreateRequestDto sourceDto);

    default User getAuthUser(){
        return (User)SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }
}
