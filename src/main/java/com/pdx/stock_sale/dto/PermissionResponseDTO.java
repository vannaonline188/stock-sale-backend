package com.pdx.stock_sale.dto;

import lombok.Data;

@Data
public class PermissionResponseDTO {
    private String Id;
    private String name;
    private String category;
    private String description;
    private String status;
    private String createdBy;
    private String createdAt;
    private String updatedBy;
    private String updatedAt;
}
