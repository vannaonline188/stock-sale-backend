package com.pdx.stock_sale.dto.master;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PermissionResponseDTO {
    private String id;
    private String name;
    private String category;
    private String description;
    private String status;
    private String createdBy;
    private String createdAt;
    private String updatedBy;
    private String updatedAt;
}
