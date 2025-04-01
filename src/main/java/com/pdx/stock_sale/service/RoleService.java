package com.pdx.stock_sale.service;

import com.pdx.stock_sale.dto.PermissionResponseDTO;
import com.pdx.stock_sale.dto.RoleRequestDTO;
import com.pdx.stock_sale.dto.RoleResponseDTO;
import com.pdx.stock_sale.exception.DataExistException;
import com.pdx.stock_sale.exception.DataNotFoundException;
import com.pdx.stock_sale.mapper.RoleMapper;
import com.pdx.stock_sale.model.PermissionEntity;
import com.pdx.stock_sale.model.RoleEntity;
import com.pdx.stock_sale.repository.PermissionRepository;
import com.pdx.stock_sale.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PermissionRepository permissionRepository;
    @Autowired
    RoleMapper roleMapper;

    public List<RoleResponseDTO> getRoles(){
        List<RoleEntity> lst_roles_entity = roleRepository.findAll();
        for(RoleEntity entity : lst_roles_entity){
            System.out.println(entity);
        }
        return lst_roles_entity.stream()
                .map(roleMapper::toDTO)
                .collect(Collectors.toList());
    }
    public RoleResponseDTO getRoleById(Integer id){
        RoleEntity entity = roleRepository.findById(id).orElseThrow(
                () -> new DataNotFoundException("Permission not found : " + id));
        return roleMapper.toDTO(entity);
    }
    public RoleResponseDTO createRole(RoleRequestDTO dto){
        if(roleRepository.existsByName(dto.getName())){
            throw new DataExistException("Data already existed.");
        }
        RoleEntity data = roleMapper.toModel(dto);
        data.setCreatedBy(1);
        data.setCreatedAt(LocalDateTime.now());
        return roleMapper.toDTO(roleRepository.save(data));
    }

    public RoleResponseDTO updateRole(Integer id, RoleRequestDTO dto){
        RoleEntity entity = roleRepository.findById(id).orElseThrow(
                () -> new DataNotFoundException("Role not found : " + id));

        entity.setUpdatedBy(1);
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setStatus(Boolean.parseBoolean(dto.getStatus()));
        entity.setUpdatedBy(1);
        entity.setUpdatedAt(LocalDateTime.now());
        Set<PermissionEntity> permissionEntitySet = new HashSet<>();
        if(dto.getPermissions() != null){
            for(String permissionId : dto.getPermissions()){
                permissionRepository.findById(Integer.parseInt(permissionId)).ifPresent(permissionEntitySet::add);
            }
        }
        entity.setPermissions(permissionEntitySet);
        return roleMapper.toDTO(roleRepository.save(entity));
    }

    public void deleteRole(Integer id) {
        roleRepository.deleteById(id);
    }
}
