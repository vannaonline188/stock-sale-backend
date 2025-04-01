package com.pdx.stock_sale.dto;

import lombok.Data;

import java.util.Set;

@Data
public class PermissionRequestDTO {
    private String name;
    private String category;
    private String description;
    private String status;
}
