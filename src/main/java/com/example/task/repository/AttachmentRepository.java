package com.example.task.repository;

import com.example.task.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AttachmentRepository extends JpaRepository<Attachment,Long> {

    @Query("select a from attachments a where a.application.id = :applicationId")
    List<Attachment> getByApplicationId(Long applicationId);

    @Query("delete from attachments a where a.id = :attachmentId")
    void removeById(Long attachmentId);
}
