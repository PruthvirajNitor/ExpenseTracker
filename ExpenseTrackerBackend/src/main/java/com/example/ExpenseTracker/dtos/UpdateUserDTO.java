package com.example.ExpenseTracker.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UpdateUserDTO {

    private String username;
    private String password;
    private String email;
    private String phoneNumber;
}
