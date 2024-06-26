package com.example.task.mapper;

import com.example.task.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;

public interface BaseMapper {
    default User getAuthUser(){
        return (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }
}
