package com.example.task.service.impl;

import com.example.task.entity.Application;
import com.example.task.mapper.ApplicationAuditMapper;
import com.example.task.repository.ApplicationAuditRepository;
import com.example.task.repository.ApplicationRepository;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class ApplicationServiceAuditImpl extends ApplicationServiceImpl{

    private final ApplicationAuditRepository applicationAuditRepository;
    private final ApplicationAuditMapper applicationAuditMapper;
    public ApplicationServiceAuditImpl(
            ApplicationRepository applicationRepository,
            ApplicationAuditRepository applicationAuditRepository,
            ApplicationAuditMapper applicationAuditMapper
    ) {
        super(applicationRepository);
        this.applicationAuditRepository = applicationAuditRepository;
        this.applicationAuditMapper = applicationAuditMapper;
    }



    @Override
    @Transactional
    public void update(Application application) {
        super.update(application);
        applicationAuditRepository.save(
                applicationAuditMapper.baseToAudit(application)
        );
    }

    @Override

    public Application create(Application task) {
        Application application = super.create(task);
        applicationAuditRepository.save(
                applicationAuditMapper.baseToAudit(application)
        );
        return application;
    }
}
