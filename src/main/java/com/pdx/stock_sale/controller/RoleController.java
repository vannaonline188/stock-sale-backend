package com.pdx.stock_sale.controller;

import com.pdx.stock_sale.dto.master.PermissionRequestDTO;
import com.pdx.stock_sale.dto.master.PermissionResponseDTO;
import com.pdx.stock_sale.dto.master.RoleRequestDTO;
import com.pdx.stock_sale.dto.master.RoleResponseDTO;
import com.pdx.stock_sale.service.RoleService;
import jakarta.validation.groups.Default;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/role")
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping("/all")
    public ResponseEntity<List<RoleResponseDTO>> createPermission(){
        List<RoleResponseDTO> responseDTOList = roleService.getRoles();
        return ResponseEntity.ok().body(responseDTOList);
    }

    @PostMapping("/create")
    public ResponseEntity<RoleResponseDTO> createPermission(
            @Validated({Default.class})
            @RequestBody RoleRequestDTO requestDTO) {
        RoleResponseDTO responseDTO = roleService.createRole(requestDTO);
        return ResponseEntity.ok().body(responseDTO);
    }
}
