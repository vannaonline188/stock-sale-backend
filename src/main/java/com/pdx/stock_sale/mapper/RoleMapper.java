package com.pdx.stock_sale.mapper;

import com.pdx.stock_sale.dto.PermissionResponseDTO;
import com.pdx.stock_sale.dto.RoleRequestDTO;
import com.pdx.stock_sale.dto.RoleResponseDTO;
import com.pdx.stock_sale.model.PermissionEntity;
import com.pdx.stock_sale.model.RoleEntity;
import com.pdx.stock_sale.repository.PermissionRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class RoleMapper {

    private PermissionRepository permissionRepository;
    private PermissionMapper permissionMapper;

    public RoleMapper(PermissionRepository permissionRepository, PermissionMapper permissionMapper) {
        this.permissionRepository = permissionRepository;
        this.permissionMapper = permissionMapper;
    }

    public RoleResponseDTO toDTO(RoleEntity data){
        RoleResponseDTO dto = new RoleResponseDTO();
        dto.setId(String.valueOf(data.getId()));
        dto.setName(data.getName());
        dto.setDescription(data.getDescription());
        dto.setStatus(String.valueOf(data.getStatus()));
        dto.setCreatedBy(String.valueOf(data.getCreatedBy()));
        dto.setCreatedAt(String.valueOf(data.getCreatedAt()));
        dto.setUpdatedBy(String.valueOf(data.getUpdatedBy()));
        Optional.ofNullable(data.getUpdatedBy()).ifPresent(updatedByStr -> dto.setUpdatedBy(String.valueOf(updatedByStr)));
        Optional.ofNullable(data.getUpdatedAt()).ifPresent(updatedAtStr -> dto.setUpdatedAt(String.valueOf(updatedAtStr)));

        Set<PermissionResponseDTO> permissionResponseDTOSet = new HashSet<>();
        System.out.println("================"+data.getPermissions().stream().count());
        if (data.getPermissions() != null) {
            permissionResponseDTOSet.addAll(data.getPermissions().stream()
                    .map(permissionMapper::toDTO)
                    .collect(Collectors.toSet()));
        }

        dto.setPermissions(permissionResponseDTOSet);
//
//        Set<PermissionResponseDTO> permissionResponseDTOSet = new HashSet<>();
//        if(data.getPermissions() !=null){
//            for(PermissionEntity joinEntity : data.getPermissions()){
//                permissionResponseDTOSet.add(permissionMapper.toDTO(joinEntity));
//            }
//        }
//        dto.setPermissions(permissionResponseDTOSet);
        return dto;
    }

    public RoleEntity toModel(RoleRequestDTO dto){
        RoleEntity data = new RoleEntity();
        data.setName(dto.getName());
        data.setDescription(dto.getDescription());
        data.setStatus(Boolean.parseBoolean(dto.getStatus()));
        Set<PermissionEntity> permissionEntitySet = new HashSet<>();
        if(dto.getPermissions() != null){
            for(String permissionId : dto.getPermissions()){
                permissionRepository.findById(Integer.parseInt(permissionId)).ifPresent(permissionEntitySet::add);
            }
        }
        data.setPermissions(permissionEntitySet);
        return data;
    }
}
