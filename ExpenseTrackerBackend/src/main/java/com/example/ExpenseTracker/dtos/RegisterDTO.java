package com.example.ExpenseTracker.dtos;


import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterDTO {

    private String userName;
    private String email;
    private String password;
    private String phoneNumber;
}
