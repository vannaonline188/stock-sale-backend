package com.pdx.stock_sale.dto.master;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {
    private Integer id;

    @NotBlank(message = "Username is required")
    @Size(max = 65, message = "Username must be at most 65 characters")
    private String username;

    @NotBlank(message = "Password is required")
    @Size(max = 255, message = "Password must be at most 255 characters")
    private String password;

    @NotBlank(message = "First name is required")
    @Size(max = 65, message = "First name must be at most 65 characters")
    private String fName;

    @NotBlank(message = "Last name is required")
    @Size(max = 65, message = "Last name must be at most 65 characters")
    private String lName;

    @NotBlank(message = "Position is required")
    @Size(max = 65, message = "Position must be at most 65 characters")
    private String position;

    @Size(max = 255, message = "Phone must be at most 255 characters")
    private String phone;

    private String email;

    private String telegramId;

    private String address;

    private Boolean status;
    private Integer createdBy;
    private Integer updatedBy;
}
