package com.example.task.service.impl;

import com.example.task.entity.Application;
import com.example.task.entity.Attachment;
import com.example.task.exception.BaseLogicException;
import com.example.task.repository.AttachmentRepository;
import com.example.task.service.ApplicationService;
import com.example.task.service.AttachmentService;
import com.example.task.service.S3Service;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AttachmentServiceImpl implements AttachmentService {

    private final S3Service s3Service;
    private final ApplicationService applicationService;

    private final AttachmentRepository attachmentRepository;

    @Override
    public void add(Attachment attachment) {
        try{
            attachmentRepository.save(attachment);
        }catch (Exception ex){
            throw new BaseLogicException("Не удалось сохранить файл");
        }
    }

    @Override
    public List<Attachment> getAllByApplication(Application application) {
        applicationService.getById(application.getId());
        List<Attachment> attachments = attachmentRepository.getByApplicationId(application.getId());
        if(attachments.isEmpty()){
            throw new BaseLogicException("Не удалось найти закрепленных файлов");
        }
        attachments.forEach(x ->
                x.setPresignedFileUrl(
                        s3Service.getPresignedUrl(x.getFilePath())
                )
        );
        return attachments;
    }



    @Override
    @Transactional
    public void removeById(Long attachmentId) {
        try{
            Attachment attachment = findById(attachmentId);
            attachmentRepository.delete(attachment);
            s3Service.removeByKey(attachment.getFilePath());
        }catch (Exception ex){
            throw new BaseLogicException("Не удалось удалить файл");
        }
    }

    @Override
    public Attachment findById(Long attachmentId) {
        return attachmentRepository.findById(attachmentId)
                .orElseThrow(() -> new BaseLogicException("Не удалось найти прикрепленный файл"));
    }

    @Override
    @Transactional
    public void removeAll() {
        attachmentRepository.deleteAll();
        s3Service.removeAll();
    }
}
