package com.pdx.stock_sale.mapper;

import com.pdx.stock_sale.dto.master.RoleRequestDTO;
import com.pdx.stock_sale.dto.master.RoleResponseDTO;
import com.pdx.stock_sale.model.master.RoleEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class RoleMapper {
    public static RoleResponseDTO toDTO(RoleEntity data){
        RoleResponseDTO dto = new RoleResponseDTO();
        dto.setId(String.valueOf(data.getId()));
        dto.setName(data.getName());
        dto.setDescription(data.getDescription());
        dto.setCreatedBy(String.valueOf(data.getCreatedBy()));
        dto.setCreatedAt(String.valueOf(data.getCreatedAt()));
        dto.setUpdatedBy(String.valueOf(data.getUpdatedBy()));
        Optional.ofNullable(dto.getUpdatedBy()).ifPresent(updatedByStr -> data.setUpdatedBy(Integer.parseInt(updatedByStr)));
        Optional.ofNullable(dto.getUpdatedAt()).ifPresent(updatedAtStr -> data.setUpdatedAt(LocalDateTime.parse(updatedAtStr)));
        return dto;
    }

    public static RoleEntity toModel(RoleRequestDTO dto){
        RoleEntity data = new RoleEntity();
        data.setName(dto.getName());
        data.setDescription(dto.getDescription());
        data.setStatus(Boolean.parseBoolean(dto.getStatus().toString()));
        data.setCreatedBy(Integer.parseInt(dto.getCreatedBy().toString()));
        Optional.ofNullable(dto.getUpdatedBy().toString()).ifPresent(updatedByStr -> data.setUpdatedBy(Integer.parseInt(updatedByStr)));
        return data;
    }
}
