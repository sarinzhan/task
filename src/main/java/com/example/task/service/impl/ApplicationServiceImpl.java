package com.example.task.service.impl;

import com.example.task.entity.Application;
import com.example.task.entity.User;
import com.example.task.entity.enums.ApplicationPriority;
import com.example.task.entity.enums.ApplicationStatus;
import com.example.task.exception.BaseLogicException;
import com.example.task.repository.ApplicationRepository;
import com.example.task.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;

    @Override
    public Application create(Application task) {
        try{
            return applicationRepository.save(task);
        }catch (Exception ex){
            throw new BaseLogicException(ex.getMessage());
        }
    }

    @Override
    public List<Application> getAll() {
        List<Application> taskList = applicationRepository.findAll();
        if(taskList.isEmpty()){
            throw new BaseLogicException("Не удалось найти заявки");
        }
        return taskList;
    }

    @Override
    public List<Application> getAllByUserId(Long userId) {

        List<Application> allByUserId = applicationRepository.getAllByCreatedUserId(userId);
        if(allByUserId.isEmpty()){
            throw new BaseLogicException("Не удалось найти заявки");
        }
        return allByUserId;
    }

    @Override
    public List<Application> getAllByStatus(ApplicationStatus applicationStatus) {
        List<Application> allApplicationByStatus =
                applicationRepository.getAllByStatus(applicationStatus);
        if(allApplicationByStatus.isEmpty()){
            throw new BaseLogicException("Не удалось найти заявки");
        }
        return allApplicationByStatus;
    }

    @Override
    public Application getById(Long applicationId) {
        return applicationRepository.findById(applicationId)
                .orElseThrow(() -> new BaseLogicException("Не удалось найти заявку"));
    }

    @Override
    public List<Application> getAllByPriority(ApplicationPriority applicationPriority) {
        List<Application> allApplicationByPriority =
                applicationRepository.getAllByPriority(applicationPriority);
        if(allApplicationByPriority.isEmpty()){
            throw new BaseLogicException("Не удалось найти заявки");
        }
        return allApplicationByPriority;
    }

    @Override
    public List<Application> getAllByCreatedUser(User user) {
        List<Application> allByUserId =
                applicationRepository.getAllByCreatedUserId(user.getId());
        if(allByUserId.isEmpty()){
            throw new BaseLogicException("Не удалось найти заявки");
        }
        return allByUserId;
    }

    @Override
    public List<Application> getAllByAssignedUser(User user) {
        List<Application> allByUserId =
                applicationRepository.getAllByAssignedUserId(user.getId());
        if(allByUserId.isEmpty()){
            throw new BaseLogicException("Не удалось найти заявки");
        }
        return allByUserId;
    }

    @Override
    public void update(Application application) {
        getById(application.getId());
        try{
            applicationRepository.save(application);
        }catch (Exception ex){
            throw new BaseLogicException("Не удалось обновить заявку");
        }
    }
}
