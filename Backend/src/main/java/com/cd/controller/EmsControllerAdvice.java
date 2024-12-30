package com.cd.controller;


import com.cd.exceptions.UserIdMismatch;
import com.cd.utils.ErrorResponse;
import com.cd.exceptions.UserNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class EmsControllerAdvice {

    @ExceptionHandler
    public ResponseEntity handleException(UserNotFound ex){
        ErrorResponse error = new ErrorResponse();
        error.setErrorCode("404");
        error.setErrorMessage("Employee Not Found please enter valid EID");
        error.setTimestamp(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler
    public ResponseEntity handleException(UserIdMismatch ex){
        ErrorResponse error = new ErrorResponse();
        error.setErrorCode("400");
        error.setErrorMessage("Bad Request");
        error.setTimestamp(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler
    public ResponseEntity handleError(MethodArgumentNotValidException ex){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorCode("400");
        errorResponse.setErrorMessage("Employee is Not Valid!");
        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setFieldErrors(ex.getFieldErrors());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);

    }

}
