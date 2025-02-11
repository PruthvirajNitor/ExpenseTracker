package com.example.ExpenseTracker.services;

import com.example.ExpenseTracker.entites.User;

import java.util.List;

public interface UserService {

    public User findByEmail(String email);
    public User createUser(User user);
    public User findUserById(Long id);
    public List<User> getAllUsers();
    public User updateUser(Long existingUserId, User updatedDetails);
}
