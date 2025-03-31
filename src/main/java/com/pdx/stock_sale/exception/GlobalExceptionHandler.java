package com.pdx.stock_sale.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @ExceptionHandler(DataExistException.class)
    public ResponseEntity<Map<String, String>> handleDataExistException(DataExistException ex) {
        log.warn("DataExistException {}", ex.getMessage());

        Map<String, String> errors = new HashMap<>();
        errors.put("message", "Data already existed");

        return ResponseEntity.badRequest().body(errors);
    }
}
