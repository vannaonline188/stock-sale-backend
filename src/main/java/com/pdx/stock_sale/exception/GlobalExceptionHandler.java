package com.pdx.stock_sale.exception;

import com.pdx.stock_sale.dto.ErrorDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @ExceptionHandler(DataExistException.class)
    public ResponseEntity<ErrorDTO> handleDataExistException(DataExistException ex) {
        ErrorDTO errors = new ErrorDTO();
        errors.setStatus(HttpStatus.BAD_REQUEST);
        errors.setMessage("Data already existed");
        log.warn("DataExistException {}", ex.getMessage());
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleDataNotFoundException(DataNotFoundException ex) {
        ErrorDTO errors = new ErrorDTO();
        errors.setStatus(HttpStatus.BAD_REQUEST);
        errors.setMessage("Data not found");
        log.warn("DataExistException {}", ex.getMessage());
        return ResponseEntity.badRequest().body(errors);
    }
}
