package com.example.task.service;

import com.example.task.entity.Users;

import java.util.List;

public interface UserService {
    void create(Users user);

    Boolean auth(Users user);

    void editPassword(Users user, String newPassword);

    List<Users> getAll();

}
