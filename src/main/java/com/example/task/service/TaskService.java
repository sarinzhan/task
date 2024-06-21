package com.example.task.service;

import com.example.task.entity.Task;

import java.util.List;

public interface TaskService {
    Task create(Task task);

    List<Task> getAll();
    List<Task> getAllByUserId(Long userId);
}
