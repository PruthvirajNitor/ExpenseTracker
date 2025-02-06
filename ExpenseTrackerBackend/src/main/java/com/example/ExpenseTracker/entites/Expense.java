package com.example.ExpenseTracker.entites;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "expenses")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty //it forces id to included in json
    private long id;

    @NotEmpty(message = "Title cannot be empty or null") //it is used so that title can nor be null nor be empty ie ""
    @Size(max = 255,message = "title cannot be more than 255 characters")
    private String title;

    @NotNull
    private String amount;

    @Enumerated(EnumType.STRING) //saved in database as a string and not ordinal
    private Category category;
    private LocalDate day;
}
