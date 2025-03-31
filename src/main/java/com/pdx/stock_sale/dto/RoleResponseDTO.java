package com.pdx.stock_sale.dto;

import lombok.Data;

import java.util.Set;

@Data
public class RoleResponseDTO {
    private String Id;
    private String name;
    private String description;
    private String status;
    private String createdBy;
    private String createdAt;
    private String updatedBy;
    private String updatedAt;

    private Set<PermissionResponseDTO> permissions;
}
