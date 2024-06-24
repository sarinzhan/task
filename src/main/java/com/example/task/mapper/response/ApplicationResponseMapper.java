package com.example.task.mapper.response;

import com.example.task.dto.response.ApplicationResponseDto;
import com.example.task.entity.Application;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ApplicationResponseMapper {

    @Mappings({
            @Mapping(target = "createdUserId", source = "createdBy.id"),
            @Mapping(target = "assignedUserId", source = "assignedTo.id")
    })
    ApplicationResponseDto entityToDto(Application task);

    List<ApplicationResponseDto> listEntityToDto(List<Application> task);

}
