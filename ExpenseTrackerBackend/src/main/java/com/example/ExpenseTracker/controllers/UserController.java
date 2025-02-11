package com.example.ExpenseTracker.controllers;


import com.example.ExpenseTracker.dtos.LoginDTO;
import com.example.ExpenseTracker.dtos.RegisterDTO;
import com.example.ExpenseTracker.dtos.UpdateUserDTO;
import com.example.ExpenseTracker.entites.User;
import com.example.ExpenseTracker.exceptions.CustomException;
import com.example.ExpenseTracker.services.UserServiceIMPL;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class UserController {

    @Autowired
    private UserServiceIMPL userService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/user/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterDTO registerDTO){

        User user = modelMapper.map(registerDTO,User.class);
        User registeredUser = userService.createUser(user);

        if(registeredUser == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Credentials are invalid");
        }
        return ResponseEntity.ok().body(registeredUser);

    }

    @PostMapping("/user/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDTO loginDTO){

        User user = userService.findByEmail(loginDTO.getEmail());

        if(user == null){
            throw new CustomException("Wrong login credentials");
        }
        return ResponseEntity.ok().body(user);
    }


    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id){

        User user = userService.findUserById(id);

        if (user == null){
            throw new CustomException("Invalid user or id");
        }
        return ResponseEntity.ok().body(user);

    }

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers(){
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UpdateUserDTO userDetails){

        User user = modelMapper.map(userDetails,User.class);

        User updatedUser = userService.updateUser(id,user);

        return ResponseEntity.ok().body(updatedUser);
    }



}
