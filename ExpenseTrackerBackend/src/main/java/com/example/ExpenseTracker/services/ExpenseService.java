package com.example.ExpenseTracker.services;

import com.example.ExpenseTracker.dtos.AddExpenseDTO;
import com.example.ExpenseTracker.dtos.UpdateExpenseDTO;
import com.example.ExpenseTracker.entites.Expense;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


public interface ExpenseService {

    public Expense addExpense(AddExpenseDTO newExpenseDetails);

    public List<Expense> getAllExpenses();

    public Expense getExpenseById(Long id);

    public List<Expense> getExpenseByUserId(Long userId);

    public Expense updateExpense(Long existingExpenseId, UpdateExpenseDTO newExpenseDetails);

    public Long deleteExpense(Long expenseId);

    public List<Expense> getExpensesBefore(LocalDate date);

    public List<Expense> getExpensesBetween(LocalDate before,LocalDate after);
}
