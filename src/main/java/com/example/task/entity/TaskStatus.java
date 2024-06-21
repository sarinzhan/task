package com.example.task.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "taskstatus")
@Data
public class TaskStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 50, unique = true, nullable = false)
    private String name;

}
