package com.pdx.stock_sale.controller;

import com.pdx.stock_sale.dto.PermissionResponseDTO;
import com.pdx.stock_sale.dto.RoleRequestDTO;
import com.pdx.stock_sale.dto.RoleResponseDTO;
import com.pdx.stock_sale.model.RoleEntity;
import com.pdx.stock_sale.service.RoleService;
import jakarta.validation.groups.Default;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/api/role")
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping("/")
    public ResponseEntity<List<RoleResponseDTO>> Roles(){
        List<RoleResponseDTO> response = roleService.getRoles();
        return ResponseEntity.ok().body(response);
    }
    @PostMapping("/{id}")
    public ResponseEntity<RoleResponseDTO> getById(@PathVariable Integer id){
        RoleResponseDTO response = roleService.getRoleById(id);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/create")
    public ResponseEntity<RoleResponseDTO> createRole(
            @Validated({Default.class})
            @RequestBody RoleRequestDTO requestDTO) {
        RoleResponseDTO responseDTO = roleService.createRole(requestDTO);
        return ResponseEntity.ok().body(responseDTO);
    }
    @PostMapping("/update/{id}")
    public ResponseEntity<RoleResponseDTO> updateRole(
            @Validated({Default.class})
            @PathVariable Integer id,
            @RequestBody RoleRequestDTO requestDTO) {
        RoleResponseDTO responseDTO = roleService.updateRole(id,requestDTO);
        return ResponseEntity.ok().body(responseDTO);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Integer id) {
        roleService.deleteRole(id);
        return ResponseEntity.noContent().build();
    }
}
