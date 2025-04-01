package com.pdx.stock_sale.mapper;

import com.pdx.stock_sale.dto.PermissionResponseDTO;
import com.pdx.stock_sale.dto.RoleResponseDTO;
import com.pdx.stock_sale.dto.UserRequestDTO;
import com.pdx.stock_sale.dto.UserResponseDTO;
import com.pdx.stock_sale.model.PermissionEntity;
import com.pdx.stock_sale.model.RoleEntity;
import com.pdx.stock_sale.model.UserEntity;
import com.pdx.stock_sale.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    @Autowired
    RoleMapper roleMapper;
    @Autowired
    RoleRepository roleRepository;
    public UserResponseDTO toDTO(UserEntity data){
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(String.valueOf(data.getId()));
        dto.setUsername(data.getUsername());
        dto.setPassword(data.getPassword());
        dto.setFirstName(data.getFirstName());
        dto.setLastName(data.getLastName());
        dto.setPosition(data.getPosition());
        dto.setPhone(data.getPhone());
        dto.setEmail(data.getEmail());
        dto.setTelegramId(data.getTelegramId());
        dto.setAddress(data.getAddress());
        dto.setStatus(String.valueOf(data.getStatus()));
        dto.setCreatedBy(String.valueOf(data.getCreatedBy()));
        dto.setCreatedAt(String.valueOf(data.getCreatedAt()));
        Optional.ofNullable(data.getUpdatedBy()).ifPresent(updatedByStr -> dto.setUpdatedBy(String.valueOf(updatedByStr)));
        Optional.ofNullable(data.getUpdatedAt()).ifPresent(updatedAtStr -> dto.setUpdatedAt(String.valueOf(updatedAtStr)));

        Set<RoleResponseDTO> roleResponseDTOSet = new HashSet<>();
        if (data.getRoles() != null) {
            roleResponseDTOSet.addAll(data.getRoles().stream()
                    .map(roleMapper::toDTO)
                    .collect(Collectors.toSet()));
        }
        dto.setRoles(roleResponseDTOSet);
        return dto;
    }

    public UserEntity toModel(UserRequestDTO dto){
        UserEntity data = new UserEntity();
        data.setUsername(dto.getUsername());
        data.setPassword(dto.getPassword());
        data.setFirstName(dto.getFirstName());
        data.setLastName(dto.getLastName());
        data.setPosition(dto.getPosition());
        data.setPhone(dto.getPhone());
        data.setEmail(dto.getEmail());
        data.setTelegramId(dto.getTelegramId());
        data.setAddress(dto.getAddress());
        data.setStatus(Boolean.parseBoolean(dto.getStatus()));

        Set<RoleEntity> roleEntitySet = new HashSet<>();
        if(dto.getRoles() != null){
            for(String permissionId : dto.getRoles()){
                roleRepository.findById(Integer.parseInt(permissionId)).ifPresent(roleEntitySet::add);
            }
        }
        data.setRoles(roleEntitySet);
        return data;
    }
}
