package com.pdx.stock_sale.service;

import com.pdx.stock_sale.dto.PermissionRequestDTO;
import com.pdx.stock_sale.dto.PermissionResponseDTO;
import com.pdx.stock_sale.exception.DataExistException;
import com.pdx.stock_sale.exception.DataNotFoundException;
import com.pdx.stock_sale.mapper.PermissionMapper;
import com.pdx.stock_sale.model.PermissionEntity;
import com.pdx.stock_sale.model.RoleEntity;
import com.pdx.stock_sale.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PermissionService {
    @Autowired
    PermissionRepository permissionRepository;
    @Autowired
    PermissionMapper permissionMapper;

    public List<PermissionResponseDTO> getPermissions(){
        List<PermissionEntity> permissions = permissionRepository.findAll();
        return permissions.stream()
                .map(permissionMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PermissionResponseDTO getPermissionById(Integer id){
        PermissionEntity entity = permissionRepository.findById(id).orElseThrow(
                () -> new DataNotFoundException("Permission not found : " + id));
        return permissionMapper.toDTO(entity);
    }

    public PermissionResponseDTO createPermission(PermissionRequestDTO dto){
        if(permissionRepository.existsByNameAndCategory(dto.getName(),dto.getCategory())){
            throw new DataExistException("Data already existed.");
        }
        PermissionEntity data = permissionMapper.toModel(dto);
        data.setCreatedBy(1);
        data.setCreatedAt(LocalDateTime.now());

        return permissionMapper.toDTO(permissionRepository.save(data));
    }
    public PermissionResponseDTO updatePermission(Integer id,PermissionRequestDTO dto){
        PermissionEntity entity = permissionRepository.findById(id).orElseThrow(
                () -> new DataNotFoundException("Permission not found : " + id));
        entity.setUpdatedBy(1);
        entity.setName(dto.getName());
        entity.setCategory(dto.getCategory());
        entity.setDescription(dto.getDescription());
        entity.setStatus(Boolean.parseBoolean(dto.getStatus()));
        entity.setUpdatedBy(1);
        entity.setUpdatedAt(LocalDateTime.now());


        return permissionMapper.toDTO(permissionRepository.save(entity));
    }
    public void deletePermission(Integer id) {
        permissionRepository.deleteById(id);
    }
}
