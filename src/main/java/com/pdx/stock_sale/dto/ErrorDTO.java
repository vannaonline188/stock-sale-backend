package com.pdx.stock_sale.dto;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ErrorDTO {
    private HttpStatus status;
    private String message;
}
