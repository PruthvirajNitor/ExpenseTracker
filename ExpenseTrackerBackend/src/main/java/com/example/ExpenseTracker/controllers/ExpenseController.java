package com.example.ExpenseTracker.controllers;

import com.example.ExpenseTracker.dtos.AddExpenseDTO;
import com.example.ExpenseTracker.dtos.UpdateExpenseDTO;
import com.example.ExpenseTracker.entites.Expense;
import com.example.ExpenseTracker.entites.User;
import com.example.ExpenseTracker.exceptions.CustomException;
import com.example.ExpenseTracker.services.ExpenseService;
import com.example.ExpenseTracker.services.ExpenseServiceIMPL;
import com.example.ExpenseTracker.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private UserService userService;

    //Add a new expense
    @PostMapping("/expense")
    public ResponseEntity<Expense> addExpense(@RequestBody AddExpenseDTO newExpenseDetails){

        Expense newExpense = expenseService.addExpense(newExpenseDetails);

        return ResponseEntity.status(HttpStatus.CREATED).body(newExpense);
    }

    //get all expenses
    @GetMapping("/expenses")
    public ResponseEntity<?> getAllExpenses(){
        return ResponseEntity.ok().body(expenseService.getAllExpenses());
    }

    //get expense by ID
    @GetMapping("/expense/{id}")
    public ResponseEntity<Expense> getExpenseById(@PathVariable Long id){
        Expense expense = expenseService.getExpenseById(id);

        return ResponseEntity.ok().body(expense);
    }

    //get expense by user id
    @GetMapping("/expenses/{userId}")
    public ResponseEntity<?> getExpenseByUserId(@PathVariable Long userId){
        User user = userService.findUserById(userId);
        if (user == null){
            throw new CustomException("Invalid ID or User");
        }

        return ResponseEntity.ok().body(expenseService.getExpenseByUserId(userId));
    }

    //update expense by id
    @PutMapping("/expense/{expenseId}")
    public ResponseEntity<Expense> updateExpense(@PathVariable Long expenseId, @RequestBody UpdateExpenseDTO updateExpenseDetails){

        Expense updatedExpense = expenseService.updateExpense(expenseId,updateExpenseDetails);
        return ResponseEntity.ok().body(updatedExpense);
    }

    //delete expense by id
    @DeleteMapping("/expense/{expenseId}")
    public ResponseEntity<Long> deleteExpense(@PathVariable Long expenseId){

        Long deletedExpenseId =  expenseService.deleteExpense(expenseId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(deletedExpenseId);
    }

    //get expenses before a date
    @GetMapping("/expenses/beforedate")
    public ResponseEntity<?> getExpensesBefore(@RequestBody LocalDate date){
        List<Expense> expenses = expenseService.getExpensesBefore(date);
        return ResponseEntity.ok().body(expenses);
    }

    //get expenses after a date
    @GetMapping("/expenses/between")
    public ResponseEntity<?> getExpensesBetween(@RequestParam LocalDate before, @RequestParam LocalDate after){
        List<Expense> expenses = expenseService.getExpensesBetween(before,after);
        return ResponseEntity.ok().body(expenses);
    }

}
