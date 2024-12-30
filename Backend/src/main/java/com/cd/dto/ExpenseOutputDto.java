package com.cd.dto;

import com.cd.entity.Category;
import com.cd.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseOutputDto {
    private int id;

    private String title;
    private double amount;

    @Enumerated(EnumType.STRING)
    private Category category;

    private LocalDate date;
    private int userId;
}
