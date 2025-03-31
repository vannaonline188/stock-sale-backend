package com.pdx.stock_sale.service;

import com.pdx.stock_sale.dto.RoleRequestDTO;
import com.pdx.stock_sale.dto.RoleResponseDTO;
import com.pdx.stock_sale.exception.DataExistException;
import com.pdx.stock_sale.mapper.RoleMapper;
import com.pdx.stock_sale.model.RoleEntity;
import com.pdx.stock_sale.repository.PermissionRepository;
import com.pdx.stock_sale.repository.RoleRepository;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleService {
    private RoleRepository roleRepository;
    private RoleMapper roleMapper;
    private PermissionRepository permissionRepository;
    public RoleService(RoleRepository roleRepository, RoleMapper roleMapper, PermissionRepository permissionRepository) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
        this.permissionRepository = permissionRepository;
    }
    public List<RoleResponseDTO> getRoles(){
        List<RoleEntity> lst_roles_entity = roleRepository.findAll();
        return lst_roles_entity.stream()
                .map(roleMapper::toDTO)
                .collect(Collectors.toList());
    }
    public RoleResponseDTO createRole(RoleRequestDTO dto){
        if(roleRepository.existsByName(dto.getName())){
            throw new DataExistException("Data already existed.");
        }
        RoleEntity data = roleMapper.toModel(dto);
        data.setCreatedBy(1);
        data.setCreatedAt(LocalDateTime.now());
        data.setUpdatedBy(1);
        data.setUpdatedAt(LocalDateTime.now());
        return roleMapper.toDTO(roleRepository.save(data));
    }
}
