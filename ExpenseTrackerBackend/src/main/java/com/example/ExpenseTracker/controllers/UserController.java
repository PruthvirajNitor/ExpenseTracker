package com.example.ExpenseTracker.controllers;


import com.example.ExpenseTracker.dtos.LoginDTO;
import com.example.ExpenseTracker.dtos.RegisterDTO;
import com.example.ExpenseTracker.entites.User;
import com.example.ExpenseTracker.exceptions.CustomException;
import com.example.ExpenseTracker.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @RequestMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterDTO registerDTO){

        User user = modelMapper.map(registerDTO,User.class);
        User registeredUser = userService.createUser(user);

        if(registeredUser == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Credentials are invalid");
        }
        return ResponseEntity.ok().body(registeredUser);

    }

    @RequestMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDTO loginDTO){

        User user = userService.findByEmail(loginDTO.getEmail());

        if(user == null){
            throw new CustomException("Wrong login credentials");
        }
        return ResponseEntity.ok().body(user);
    }


}
