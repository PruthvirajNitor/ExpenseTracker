package com.cd.service;

import com.cd.dto.ExpenseInputDto;
import com.cd.dto.ExpenseOutputDto;
import com.cd.entity.Expense;
import com.cd.entity.User;
import com.cd.repository.ExpenseRepositoy;
import com.cd.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepositoy expenseRepository;


    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

//    public ExpenseOutputDto createExpense(ExpenseInputDto expenseInputDto) {
//        //User user = mapper.map(UserInputDto, User.class);
////        Expense expense = new Expense();
////        expense.setTitle(expenseInputDto.getTitle());
////        expense.setAmount(expenseInputDto.getAmount());
////        expense.setCategory(expenseInputDto.getCategory());
////        expense.setDate(expenseInputDto.getDate());
//        Expense expense=modelMapper.map(expenseInputDto,Expense.class);
//
//        // Fetching the User object using userId
//        User user = userRepository.findById(expenseInputDto.getUserId())
//                .orElseThrow(() -> new RuntimeException("User  not found"));
//        expense.setUser(user);
//        return modelMapper.map(expenseRepository.save((expense)),ExpenseOutputDto.class);
//
//    }
public ExpenseOutputDto createExpense(ExpenseInputDto expenseInputDto) {
    // Fetch the User entity using userId
    User user = userRepository.findById(expenseInputDto.getUserId())
            .orElseThrow(() -> new RuntimeException("User  not found"));

    // Create a new Expense entity
    Expense expense = new Expense();
    expense.setTitle(expenseInputDto.getTitle());
    expense.setAmount(expenseInputDto.getAmount());
    expense.setCategory(expenseInputDto.getCategory());
    expense.setDate(expenseInputDto.getDate());
    expense.setUser (user); // Set the User entity

    // Save the Expense entity
    Expense exp =expenseRepository.save(expense);
    return modelMapper.map(exp,ExpenseOutputDto.class);
}

    public List<ExpenseOutputDto> getAllExpenses() {
        List<Expense> expenses = expenseRepository.findAll();
        return expenses.stream()
                .map(expense -> modelMapper.map(expense, ExpenseOutputDto.class))
                .collect(Collectors.toList());
    }

    public Optional<ExpenseOutputDto> getExpenseById(int id) {
        return expenseRepository.findById(id)
                .map(expense -> modelMapper.map(expense, ExpenseOutputDto.class));
    }

    public ExpenseOutputDto updateExpense(int id, ExpenseInputDto expenseDetails) {
        return expenseRepository.findById(id)
                .map(expense -> {
                    expense.setTitle(expenseDetails.getTitle());
                    expense.setAmount(expenseDetails.getAmount());
                    expense.setCategory(expenseDetails.getCategory());
                    expense.setDate(expenseDetails.getDate());
                    Expense updatedExpense = expenseRepository.save(expense);
                    return modelMapper.map(updatedExpense, ExpenseOutputDto.class);
                })
                .orElse(null);
    }

    public void deleteExpense(int id) {
        expenseRepository.deleteById(id);
    }

    public List<ExpenseOutputDto> getExpensesByUserId(int userId) {
        List<Expense> expenses = expenseRepository.findByUserId(userId);
        // Map the Expense entities to ExpenseOutputDto
        return expenses.stream()
                .map(expense -> new ExpenseOutputDto(
                        expense.getId(),
                        expense.getTitle(),
                        expense.getAmount(),
                        expense.getCategory(),
                        expense.getDate(),
                        expense.getUser().getId()
                ))
                .collect(Collectors.toList());
    }


}