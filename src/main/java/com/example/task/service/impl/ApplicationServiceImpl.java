package com.example.task.service.impl;

import com.example.task.entity.Application;
import com.example.task.exception.BaseLogicException;
import com.example.task.repository.TaskRepository;
import com.example.task.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {

    private final TaskRepository taskRepository;

    @Override
    public Application create(Application task) {
        try{
            return taskRepository.save(task);
        }catch (Exception ex){
            throw new BaseLogicException("Не удалось сохранить задачу");
        }
    }

    @Override
    public List<Application> getAll() {
        List<Application> taskList = taskRepository.findAll();
        if(taskList.isEmpty()){
            throw new BaseLogicException("Не удалось найти задач");
        }
        return taskList;
    }

    @Override
    public List<Application> getAllByUserId(Long userId) {

        List<Application> allByUserId = taskRepository.getAllByUserId(userId);
        if(allByUserId.isEmpty()){
            throw new BaseLogicException("Не удалось найти задач");
        }
        return allByUserId;
    }
}
