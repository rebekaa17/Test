package com.example.cinema.service;

import com.example.cinema.entity.User;

import java.util.List;

public interface UserService {
    User createUser(User user);
    List<User> getAllUsers();
    User getUserById(Long id);
    User updateUser(Long id, User updatedUser);
    boolean deleteUser(Long id);
}
