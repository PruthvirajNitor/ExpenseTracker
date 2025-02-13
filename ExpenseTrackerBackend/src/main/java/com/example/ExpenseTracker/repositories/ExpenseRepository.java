package com.example.ExpenseTracker.repositories;

import com.example.ExpenseTracker.entites.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense,Long> {

    public List<Expense> findByUserId(Long userId);

    public  List<Expense> findByDayBefore(LocalDate date);

    public List<Expense> findByDayBetween(LocalDate before,LocalDate after);

}
