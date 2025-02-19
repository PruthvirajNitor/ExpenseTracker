package com.example.ExpenseTracker.entites;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.time.LocalDate;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "expenses")
public class Expense extends BaseEntity {


    @NotEmpty(message = "Title cannot be empty or null") //it is used so that title can nor be null nor be empty ie ""
    @Size(max = 255,message = "title cannot be more than 255 characters")
    private String title;

    @NotNull
    @Positive(message = "Amount cannot be negative")
    private Double amount;


    @Enumerated(EnumType.STRING) //saved in database as a string and not ordinal
    private Category category;
    private LocalDate day;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public @NotEmpty(message = "Title cannot be empty or null") @Size(max = 255, message = "title cannot be more than 255 characters") String getTitle() {
        return title;
    }

    public void setTitle(@NotEmpty(message = "Title cannot be empty or null") @Size(max = 255, message = "title cannot be more than 255 characters") String title) {
        this.title = title;
    }

    public @NotNull @Positive(message = "Amount cannot be negative") Double getAmount() {
        return amount;
    }

    public void setAmount(@NotNull @Positive(message = "Amount cannot be negative") Double amount) {
        this.amount = amount;
    }
    
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public LocalDate getDay() {
        return day;
    }

    public void setDay(LocalDate day) {
        this.day = day;
    }
}
