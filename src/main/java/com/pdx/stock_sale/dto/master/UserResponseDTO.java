package com.pdx.stock_sale.dto.master;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {
    private Integer id;

    private String username;
    private String fName;
    private String lName;
    private String position;
    private String phone;
    private String email;
    private String telegramId;
    private String address;
    private Boolean status;
    private Integer createdBy;
    private LocalDateTime createdAt;
    private Integer updatedBy;
    private LocalDateTime updatedAt;

    private Set<RoleResponseDTO> roles;

}
