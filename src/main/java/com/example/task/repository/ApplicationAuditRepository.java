package com.example.task.repository;

import com.example.task.entity.audit.ApplicationAudit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationAuditRepository extends JpaRepository<ApplicationAudit, Long> {
}
