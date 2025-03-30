package com.pdx.stock_sale.mapper;

import com.pdx.stock_sale.dto.master.PermissionRequestDTO;
import com.pdx.stock_sale.dto.master.PermissionResponseDTO;
import com.pdx.stock_sale.dto.master.RoleRequestDTO;
import com.pdx.stock_sale.dto.master.RoleResponseDTO;
import com.pdx.stock_sale.model.master.PermissionEntity;
import com.pdx.stock_sale.model.master.RoleEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class PermissionMapper {
    public static PermissionResponseDTO toDTO(PermissionEntity data){
        PermissionResponseDTO dto = new PermissionResponseDTO();
        dto.setId(data.getId().toString());
        dto.setName(data.getName());
        dto.setCategory(data.getCategory());
        dto.setDescription(data.getDescription());
        dto.setStatus(data.getStatus().toString());
        dto.setCreatedBy(String.valueOf(data.getCreatedBy()));
        dto.setCreatedAt(String.valueOf(data.getCreatedAt()));
        dto.setUpdatedBy(String.valueOf(data.getUpdatedBy()));
        Optional.ofNullable(dto.getUpdatedBy()).ifPresent(updatedByStr -> data.setUpdatedBy(Integer.parseInt(updatedByStr)));
        Optional.ofNullable(dto.getUpdatedAt()).ifPresent(updatedAtStr -> data.setUpdatedAt(LocalDateTime.parse(updatedAtStr)));
        return dto;
    }

    public static PermissionEntity toModel(PermissionRequestDTO dto){
        PermissionEntity data = new PermissionEntity();
        data.setName(dto.getName());
        data.setCategory(dto.getCategory());
        data.setDescription(dto.getDescription());
        data.setStatus(Boolean.parseBoolean(dto.getStatus().toString()));
        data.setCreatedBy(Integer.parseInt(dto.getCreatedBy().toString()));
        Optional.ofNullable(dto.getUpdatedBy().toString()).ifPresent(updatedByStr -> data.setUpdatedBy(Integer.parseInt(updatedByStr)));
        return data;
    }
}
