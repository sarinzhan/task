package com.example.task.service;

import com.example.task.entity.Application;

import java.util.List;

public interface ApplicationService {
    Application create(Application task);
    List<Application> getAll();
    List<Application> getAllByUserId(Long userId);
}
