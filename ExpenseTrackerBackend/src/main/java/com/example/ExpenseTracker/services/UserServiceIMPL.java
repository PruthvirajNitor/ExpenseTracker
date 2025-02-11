package com.example.ExpenseTracker.services;

import com.example.ExpenseTracker.entites.User;
import com.example.ExpenseTracker.exceptions.CustomException;
import com.example.ExpenseTracker.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceIMPL implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;


    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public User createUser(User user){
        return userRepository.save(user);
    }

    public User findUserById(Long id) { return userRepository.findById(id).orElseThrow();}

    public List<User> getAllUsers(){ return userRepository.findAll(); }

    public User updateUser(Long existingUserId, User updatedDetails){
        User existingUser = findUserById(existingUserId);
        if(existingUser == null){
            throw new CustomException("Invalid user ID");
        }

        existingUser.setUserName(updatedDetails.getUserName());
        existingUser.setPassword(updatedDetails.getPassword());
        existingUser.setEmail(updatedDetails.getEmail());
        existingUser.setPhoneNumber(updatedDetails.getPhoneNumber());

        return userRepository.save(existingUser);
    }
}
