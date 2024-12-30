package com.cd.repository;

import com.cd.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseRepositoy extends JpaRepository<Expense,Integer> {
   // User findByUsername(String username);
   List<Expense> findByUserId(int userId);

}
