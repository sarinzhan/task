package com.example.task.mapper;

import com.example.task.entity.Application;
import com.example.task.entity.audit.ApplicationAudit;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring", imports = {LocalDateTime.class})
public interface ApplicationAuditMapper extends BaseMapper{

    @Mappings({
            @Mapping(target = "actionAt", expression = "java(LocalDateTime.now())"),
            @Mapping(target = "actionBy", expression = "java(getAuthUser())"),
            @Mapping(target = "application", expression = "java(application)"),
    })
    ApplicationAudit baseToAudit(Application application);
}
