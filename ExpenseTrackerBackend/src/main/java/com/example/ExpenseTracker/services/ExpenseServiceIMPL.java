package com.example.ExpenseTracker.services;

import com.example.ExpenseTracker.dtos.AddExpenseDTO;
import com.example.ExpenseTracker.dtos.UpdateExpenseDTO;
import com.example.ExpenseTracker.entites.Expense;
import com.example.ExpenseTracker.exceptions.CustomException;
import com.example.ExpenseTracker.repositories.ExpenseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.CustomAutowireConfigurer;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ExpenseServiceIMPL implements ExpenseService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ExpenseRepository expenseRepository;

    @Override
    public Expense addExpense(AddExpenseDTO newExpenseDetails) {

        Expense newExpense = modelMapper.map(newExpenseDetails,Expense.class);
        return expenseRepository.save(newExpense);
    }

    @Override
    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    @Override
    public Expense getExpenseById(Long id) {

        return expenseRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Expense> getExpenseByUserId(Long userId) {

        return expenseRepository.findByUserId(userId);
    }

    @Override
    public Expense updateExpense(Long existingExpenseId, UpdateExpenseDTO newExpenseDetails) {

        Expense existingExpense = expenseRepository.findById(existingExpenseId).orElseThrow();

        existingExpense.setTitle(newExpenseDetails.getTitle());
        existingExpense.setTitle(newExpenseDetails.getTitle());
        existingExpense.setCategory(newExpenseDetails.getCategory());
        existingExpense.setDay(newExpenseDetails.getDay());

        return existingExpense;
    }

    @Override
    public Long deleteExpense(Long expenseId) {

        expenseRepository.deleteById(expenseId);
        return expenseId;
    }

    @Override
    public List<Expense> getExpensesBefore(LocalDate date) {
        return expenseRepository.findByDayBefore(date);
    }

    public List<Expense> getExpensesBetween(LocalDate before, LocalDate after){
        return expenseRepository.findByDayBetween(before,after);
    }
}
