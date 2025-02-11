package com.example.ExpenseTracker.entites;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
@Data//generates getters setters equals and hashcode
@EqualsAndHashCode(callSuper = true) //used so that equals and hashcode considers id while computing
public class User extends BaseEntity {


    @NotBlank(message = "Username cannot blank")
    private String userName;

    @NotBlank(message = "Password cannot be blank")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "Password must contain at least one lowercase, uppercase, number and character")
    private String password;

    @NotBlank(message = "email cannot be blank")
    private String email;

    @NonNull
    @Pattern(regexp = "^\\d{10}$",message = "Phone number must contain 10 digits")
    private String phoneNumber;

    @OneToMany(mappedBy = "users",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    //mapped by says this is a parent component and user maintains the relationship. Cascade says that if user is droped corresponding expenses should be dropped too.Expenses are loaded only when they are accessed
    @JsonManagedReference
    private List<Expense> expenses;
}
