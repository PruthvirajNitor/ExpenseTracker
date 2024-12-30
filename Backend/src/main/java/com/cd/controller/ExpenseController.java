package com.cd.controller;

import com.cd.dto.ExpenseInputDto;
import com.cd.dto.ExpenseOutputDto;
import com.cd.dto.UserOutputDto;
import com.cd.entity.Expense;
import com.cd.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/expense")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    // Create a new expense
    @PostMapping
    public ExpenseOutputDto createExpense(@RequestBody ExpenseInputDto expenseDto) {
        return expenseService.createExpense(expenseDto);
    }

    // Get all expenses
    @GetMapping
    public List<ExpenseOutputDto> getAllExpenses() {
        return expenseService.getAllExpenses();
    }

    // Get an expense by ID
    @GetMapping("/{id}")
    public ResponseEntity<ExpenseOutputDto> getExpenseById(@PathVariable int id) {
        return expenseService.getExpenseById(id)
                .map(expenseDto -> ResponseEntity.ok().body(expenseDto))
                .orElse(ResponseEntity.notFound().build());
    }

    // Update an expense
    @PutMapping("/{id}")
    public ResponseEntity<ExpenseOutputDto> updateExpense(@PathVariable int id, @RequestBody ExpenseInputDto expenseDetails) {
        ExpenseOutputDto updatedExpense = expenseService.updateExpense(id, expenseDetails);
        return updatedExpense != null ? ResponseEntity.ok().body(updatedExpense) : ResponseEntity.notFound().build();
    }

    // Delete an expense
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable int id) {
        expenseService.deleteExpense(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ExpenseOutputDto>> getExpensesByUserId(@PathVariable int userId) {
        try {
            List<ExpenseOutputDto> expenses = expenseService.getExpensesByUserId(userId);
            return ResponseEntity.ok(expenses);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }





}