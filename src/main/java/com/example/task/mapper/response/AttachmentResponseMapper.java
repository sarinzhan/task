package com.example.task.mapper.response;

import com.example.task.dto.response.AttachmentResponseDto;
import com.example.task.entity.Attachment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AttachmentResponseMapper {

    @Mappings({
            @Mapping(target = "applicationId", source = "application.id"),
            @Mapping(target = "uploadedUserId", source = "uploadedBy.id"),
            @Mapping(target = "fileName", source = "filePath")
    })
    AttachmentResponseDto entityToDto(Attachment attachment);


    List<AttachmentResponseDto> listEntityToDto(List<Attachment> attachment);
}
