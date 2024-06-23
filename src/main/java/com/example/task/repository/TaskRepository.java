package com.example.task.repository;

import com.example.task.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Application, Long> {

    @Query("select a from application a where a.createdBy.id = :userId")
    List<Application> getAllByUserId(Long userId);
}
