package com.pdx.stock_sale.service;

import com.pdx.stock_sale.dto.master.PermissionRequestDTO;
import com.pdx.stock_sale.dto.master.PermissionResponseDTO;
import com.pdx.stock_sale.exception.DataExistException;
import com.pdx.stock_sale.mapper.PermissionMapper;
import com.pdx.stock_sale.mapper.RoleMapper;
import com.pdx.stock_sale.model.master.PermissionEntity;
import com.pdx.stock_sale.model.master.RoleEntity;
import com.pdx.stock_sale.repository.PermissionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PermissionService {
    private final PermissionRepository permissionRepository;

    public PermissionService(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    public List<PermissionResponseDTO> getPermissions (){
        List<PermissionEntity> roles = permissionRepository.findAll();
        return roles.stream().map(PermissionMapper::toDTO).toList();
    }
    public PermissionResponseDTO createPermission(PermissionRequestDTO requestDTO){
        if(permissionRepository.existsByNameAndCategory(requestDTO.getName(),requestDTO.getCategory())){
            throw new DataExistException("Permission already existed.");
        }

        PermissionEntity data = PermissionMapper.toModel(requestDTO);
        data.setCreatedAt(LocalDateTime.now());
        System.out.println(data);

        return PermissionMapper.toDTO(permissionRepository.save(data));
    }
}
