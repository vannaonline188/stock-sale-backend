package com.pdx.stock_sale.service;
import com.pdx.stock_sale.dto.master.RoleRequestDTO;
import com.pdx.stock_sale.dto.master.RoleResponseDTO;
import com.pdx.stock_sale.exception.DataExistException;
import com.pdx.stock_sale.mapper.PermissionMapper;
import com.pdx.stock_sale.mapper.RoleMapper;
import com.pdx.stock_sale.model.master.PermissionEntity;
import com.pdx.stock_sale.model.master.RoleEntity;
import com.pdx.stock_sale.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RoleService {
    private RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<RoleResponseDTO> getRoles(){
        List<RoleEntity> roles = roleRepository.findAll();
        return roles.stream().map(RoleMapper::toDTO).toList();
    }

    public RoleResponseDTO createRole(RoleRequestDTO dto){
        if(roleRepository.existsByName(dto.getName())){
            throw new DataExistException("Permission already existed.");
        }

        RoleEntity data = RoleMapper.toModel(dto);
        data.setCreatedAt(LocalDateTime.now());
        System.out.println(data);

        return RoleMapper.toDTO(roleRepository.save(data));
    }
}
