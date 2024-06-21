package com.example.task.service;

import com.example.task.entity.Comment;
import com.example.task.entity.Task;

import java.util.List;

public interface CommentService {
    Comment create(Comment comment);
    List<Comment> getAllByTaskId(Long taskId);
}
