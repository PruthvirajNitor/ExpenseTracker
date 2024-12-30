package com.cd.controller;

import com.cd.dto.ExpenseInputDto;
import com.cd.dto.ExpenseOutputDto;
import com.cd.service.ExpenseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class ExpenseControllerTest {

    @InjectMocks
    private ExpenseController expenseController;

    @Mock
    private ExpenseService expenseService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createExpense_ShouldReturnExpenseOutputDto() {
        ExpenseInputDto expenseInputDto = new ExpenseInputDto();
        ExpenseOutputDto expenseOutputDto = new ExpenseOutputDto();
        when(expenseService.createExpense(any(ExpenseInputDto.class))).thenReturn(expenseOutputDto);

        ExpenseOutputDto result = expenseController.createExpense(expenseInputDto);

        assertNotNull(result);
        verify(expenseService, times(1)).createExpense(expenseInputDto);
    }

    @Test
    void getAllExpenses_ShouldReturnListOfExpenses() {
        when(expenseService.getAllExpenses()).thenReturn(Collections.emptyList());

        List<ExpenseOutputDto> result = expenseController.getAllExpenses();

        assertNotNull(result);
        verify(expenseService, times(1)).getAllExpenses();
    }

    @Test
    void deleteExpense_ShouldReturnNoContent() {
        doNothing().when(expenseService).deleteExpense(1);

        ResponseEntity<Void> response = expenseController.deleteExpense(1);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(expenseService, times(1)).deleteExpense(1);
    }
}
