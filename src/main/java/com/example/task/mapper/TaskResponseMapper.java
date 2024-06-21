package com.example.task.mapper;

import com.example.task.dto.response.TaskResponseDto;
import com.example.task.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskResponseMapper {

    @Mappings({
            @Mapping(target = "userFirstName", source = "user.firstName"),
            @Mapping(target = "userId", source = "user.id"),
            @Mapping(target = "status", source = "status.name")
    })
    TaskResponseDto entityToDto(Task task);

    List<TaskResponseDto> listEntityToDto(List<Task> task);

}
