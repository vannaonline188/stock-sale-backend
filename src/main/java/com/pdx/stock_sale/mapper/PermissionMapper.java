package com.pdx.stock_sale.mapper;

import com.pdx.stock_sale.dto.PermissionResponseDTO;
import com.pdx.stock_sale.dto.RoleResponseDTO;
import com.pdx.stock_sale.model.PermissionEntity;
import com.pdx.stock_sale.model.RoleEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class PermissionMapper {
    public PermissionResponseDTO toDTO(PermissionEntity data){
        PermissionResponseDTO dto = new PermissionResponseDTO();
        dto.setId(String.valueOf(data.getId()));
        dto.setName(data.getName());
        dto.setCategory(data.getCategory());
        dto.setDescription(data.getDescription());
        dto.setStatus(String.valueOf(data.getStatus()));
        dto.setCreatedBy(String.valueOf(data.getCreatedBy()));
        dto.setCreatedAt(String.valueOf(data.getCreatedAt()));
        Optional.ofNullable(data.getUpdatedBy()).ifPresent(updatedByStr -> dto.setUpdatedBy(String.valueOf(updatedByStr)));
        Optional.ofNullable(data.getUpdatedAt()).ifPresent(updatedAtStr -> dto.setUpdatedAt(String.valueOf(updatedAtStr)));
        return dto;
    }
}
