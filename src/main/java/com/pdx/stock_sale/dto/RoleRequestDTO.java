package com.pdx.stock_sale.dto;

import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class RoleRequestDTO {
    private String name;
    private String category;
    private String description;
    private String status;
    private Set<String> permissions;
}
