package com.cd.dto;

import com.cd.entity.Expense;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInputDto {
    private String userName;
    private String password; // In a real application, ensure to hash passwords
    private String email;
    private String phoneNumber;

}
