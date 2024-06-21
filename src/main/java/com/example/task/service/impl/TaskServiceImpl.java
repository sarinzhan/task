package com.example.task.service.impl;

import com.example.task.entity.Task;
import com.example.task.exception.BaseLogicException;
import com.example.task.repository.TaskRepository;
import com.example.task.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    public Task create(Task task) {
        try{
            return taskRepository.save(task);
        }catch (Exception ex){
            throw new BaseLogicException("Не удалось сохранить задачу");
        }
    }

    @Override
    public List<Task> getAll() {
        List<Task> taskList = taskRepository.findAll();
        if(taskList.isEmpty()){
            throw new BaseLogicException("Не удалось найти задач");
        }
        return taskList;
    }

    @Override
    public List<Task> getAllByUserId(Long userId) {

        List<Task> allByUserId = taskRepository.getAllByUserId(userId);
        if(allByUserId.isEmpty()){
            throw new BaseLogicException("Не удалось найти задач");
        }
        return allByUserId;
    }
}
