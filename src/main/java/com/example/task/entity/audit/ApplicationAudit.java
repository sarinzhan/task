package com.example.task.entity.audit;

import com.example.task.entity.Application;
import com.example.task.entity.User;
import com.example.task.entity.enums.ApplicationPriority;
import com.example.task.entity.enums.ApplicationStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity(name = "application_audit")
@Data
public class ApplicationAudit extends  Application{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title",  nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;

    @Column(name = "priority")
    @Enumerated(EnumType.STRING)
    private ApplicationPriority priority;

    @Column(name = "due_date")
    private LocalDateTime dueDate;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false,updatable = false)
    private User createdBy;

    @Column(name = "assigned_at")
    private LocalDateTime assignedAt;

    @ManyToOne
    @JoinColumn(name = "assigned_to")
    private User assignedTo;


    // to audit
    @ManyToOne
    @JoinColumn(name = "application_id", nullable = false)
    private Application application;

    @Column(name = "action_at", nullable = false)
    private LocalDateTime actionAt;

    @ManyToOne
    @JoinColumn(name = "action_by",nullable = false)
    private User actionBy;
}
