package com.pdx.stock_sale.dto;
import lombok.Data;
import java.util.Set;

@Data
public class UserResponseDTO {
    private String id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String position;
    private String phone;
    private String email;
    private String telegramId;
    private String address;
    private String status;
    private String createdBy;
    private String createdAt;
    private String updatedBy;
    private String updatedAt;
    Set<RoleResponseDTO> roles;
}
