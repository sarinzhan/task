package com.example.task.service;

import com.example.task.entity.Application;
import com.example.task.entity.User;
import com.example.task.entity.enums.ApplicationPriority;
import com.example.task.entity.enums.ApplicationStatus;

import java.util.List;

public interface ApplicationService {
    Application create(Application task);
    List<Application> getAll();
    List<Application> getAllByUserId(Long userId);

    List<Application> getAllByStatus(ApplicationStatus applicationStatus);

    Application getById(Long applicationId);

    List<Application> getAllByPriority(ApplicationPriority applicationPriority);

    List<Application> getAllByCreatedUser(User user);
    List<Application> getAllByAssignedUser(User user);

    void update(Application application);
}
