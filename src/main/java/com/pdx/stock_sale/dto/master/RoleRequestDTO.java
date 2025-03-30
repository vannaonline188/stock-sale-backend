package com.pdx.stock_sale.dto.master;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleRequestDTO {

    @NotBlank(message = "Role name is required.")
    private String name;
    private String description;
    private Boolean status;
    private Integer createdBy;
    private Integer updatedBy;
}
