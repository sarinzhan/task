package com.example.task.service.impl;

import com.example.task.entity.Comment;
import com.example.task.entity.Task;
import com.example.task.exception.BaseLogicException;
import com.example.task.repository.CommentRepository;
import com.example.task.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "commentCache")
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;

    @CacheEvict(allEntries = true)
    @Override
    public Comment create(Comment comment) {
        try {
            return commentRepository.save(comment);
        }catch (Exception ex){
            throw new BaseLogicException("Не удалось сохранить комментарии");
        }
    }

    @Override
    public List<Comment> getAllByTaskId(Long taskId) {
        waitMillis(3000L);
        List<Comment> comments = commentRepository.findAll();
        if(comments.isEmpty()){
            throw new BaseLogicException("Не удалось найти комментарии");
        }
        return comments;
    }


    private void waitMillis(Long millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e){
            throw new BaseLogicException("Error");
        }
    }
}
