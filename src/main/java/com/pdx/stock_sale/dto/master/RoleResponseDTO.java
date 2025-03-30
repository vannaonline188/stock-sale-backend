package com.pdx.stock_sale.dto.master;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleResponseDTO {
    private String id;
    private String name;
    private String description;
    private String status;
    private String createdBy;
    private String createdAt;
    private String updatedBy;
    private String updatedAt;

    private Set<PermissionResponseDTO> permissions;
}
