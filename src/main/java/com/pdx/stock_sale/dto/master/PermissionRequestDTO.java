package com.pdx.stock_sale.dto.master;

import com.pdx.stock_sale.exception.validation.PermissionUpdateValidation;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PermissionRequestDTO {
    private String name;
    private String category;
    private String description;

    private Boolean status;
    private Integer createdBy;
    @NotBlank(groups = PermissionUpdateValidation.class,message = "Update By is required")
    private Integer updatedBy;
}
