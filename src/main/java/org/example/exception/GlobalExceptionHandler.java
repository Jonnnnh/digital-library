package org.example.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String handleValidationExceptions(MethodArgumentNotValidException ex, Model model) {
        model.addAttribute("error", "Validation error occurred");
        return "error";
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception e, Model model) {
        model.addAttribute("error", "An unexpected error occurred: " + e.getMessage());
        return "error";
    }

    @ExceptionHandler(SQLException.class)
    public String handleSQLException(SQLException ex, Model model) {
        model.addAttribute("error", "Database error occurred");
        return "error";
    }
}

