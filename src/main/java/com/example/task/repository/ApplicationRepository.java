package com.example.task.repository;

import com.example.task.entity.Application;
import com.example.task.entity.enums.ApplicationPriority;
import com.example.task.entity.enums.ApplicationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    @Query("select a from application a where a.createdBy.id = :userId")
    List<Application> getAllByCreatedUserId(Long userId);

    @Query("select a from application a where a.assignedTo.id = :userId")
    List<Application> getAllByAssignedUserId(Long userId);

    @Query("select a from application a where a.status = :status")
    List<Application> getAllByStatus(ApplicationStatus status);

    @Query("select a from application a where a.priority = :priority")
    List<Application> getAllByPriority(ApplicationPriority priority);


}
