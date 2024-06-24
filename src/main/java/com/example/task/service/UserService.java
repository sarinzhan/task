package com.example.task.service;

import com.example.task.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserService {
    void create(User user);

    void editPassword(User user, String newPassword);

    List<User> getAll();

    UserDetails loadUserByUsername(String username);

    User findByUsername(String username);

    User findById(Long userId);
}
