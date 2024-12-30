package com.cd.controller;

import com.cd.dto.UserInputDto;
import com.cd.dto.UserOutputDto;
import com.cd.entity.User;
import com.cd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public UserInputDto createUser (@RequestBody UserInputDto UserInputDto) {
        return userService.createUser (UserInputDto);
    }

    @GetMapping
    public List<UserOutputDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserOutputDto> getUserById(@PathVariable int id) {
        return userService.getUserById(id)
                .map(UserInputDto -> ResponseEntity.ok().body(UserInputDto))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserInputDto> updateUser (@PathVariable int id, @RequestBody UserInputDto userDetails) {
        UserInputDto updatedUser  = userService.updateUser (id, userDetails);
        return updatedUser  != null ? ResponseEntity.ok().body(updatedUser ) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser (@PathVariable int id) {
        userService.deleteUser (id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserInputDto loginRequest) {
        // Fetch user by email
        User user = userService.findByEmail(loginRequest.getEmail());
        System.out.println(user);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }

        // Validate password
        if (!user.getPassword().equals(loginRequest.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }

        // Generate a response (e.g., a token or success message)
        return ResponseEntity.ok(user);
    }
}