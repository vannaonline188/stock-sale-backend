package com.pdx.stock_sale.service;

import com.pdx.stock_sale.dto.*;
import com.pdx.stock_sale.exception.DataExistException;
import com.pdx.stock_sale.exception.DataNotFoundException;
import com.pdx.stock_sale.exception.GlobalExceptionHandler;
import com.pdx.stock_sale.mapper.UserMapper;
import com.pdx.stock_sale.model.PermissionEntity;
import com.pdx.stock_sale.model.RoleEntity;
import com.pdx.stock_sale.model.UserEntity;
import com.pdx.stock_sale.repository.RoleRepository;
import com.pdx.stock_sale.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    public List<UserResponseDTO> getUsers(){
        log.info("userService > getUsers");
        List<UserEntity> lstUsers = userRepository.findAll();

        log.info("getUsers count: {}",lstUsers.size());
        return lstUsers.stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }
    public UserResponseDTO getUserById(Integer id){
        log.info("userService > getUserById");
        UserEntity entity = userRepository.findById(id).orElseThrow(
                () -> new DataNotFoundException("username not found : " + id));

        log.info("getUserById : {}",id);
        return userMapper.toDTO(entity);
    }
    public UserResponseDTO createUser(UserRequestDTO dto){
        if(userRepository.existsByUsername(dto.getUsername())){
            throw new DataExistException("username already exist.");
        }
        UserEntity data = userMapper.toModel(dto);
        data.setCreatedBy(1);
        data.setCreatedAt(LocalDateTime.now());
        return userMapper.toDTO(userRepository.save(data));
    }

    public UserResponseDTO updateUser(Integer id, UserRequestDTO dto){
        UserEntity entity = userRepository.findById(id).orElseThrow(
                () -> new DataNotFoundException("Permission not found : " + id)
        );
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setPosition(dto.getPosition());
        entity.setPhone(dto.getPhone());
        entity.setEmail(dto.getEmail());
        entity.setTelegramId(dto.getTelegramId());
        entity.setAddress(dto.getAddress());

        entity.setStatus(Boolean.parseBoolean(dto.getStatus()));
        entity.setUpdatedBy(1);
        entity.setUpdatedAt(LocalDateTime.now());

        Set<RoleEntity> roleEntitySet = new HashSet<>();
        if(dto.getRoles() != null){
            for(String roleId : dto.getRoles()){
                roleRepository.findById(Integer.parseInt(roleId)).ifPresent(roleEntitySet::add);
            }
        }
        entity.setRoles(roleEntitySet);

        return userMapper.toDTO(userRepository.save(entity));
    }

}
