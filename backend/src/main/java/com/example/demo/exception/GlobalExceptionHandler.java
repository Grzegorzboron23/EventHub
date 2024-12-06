package com.example.demo.exception;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException e) {
        ErrorResponse errorResponse = new ErrorResponse("Validation error: " + e.getMessage(), HttpStatus.BAD_REQUEST.value());
        log.error("ConstraintViolationException occurred: {}", e.getMessage(), e);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorResponse> handleNullPointerException(NullPointerException e) {
        ErrorResponse errorResponse = new ErrorResponse("Validation error: " + e.getMessage(), HttpStatus.BAD_REQUEST.value());
        log.error("NullPointerException occurred: {}", e.getMessage(), e);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleExceptions(Exception e) {
        ErrorResponse errorResponse = new ErrorResponse("Validation error: " + e.getMessage(), HttpStatus.BAD_REQUEST.value());
        log.warn("Exception occurred: {}", e.getMessage(), e);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
