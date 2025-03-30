package com.pdx.stock_sale.controller;

import com.pdx.stock_sale.dto.master.PermissionRequestDTO;
import com.pdx.stock_sale.dto.master.PermissionResponseDTO;
import com.pdx.stock_sale.exception.validation.PermissionUpdateValidation;
import com.pdx.stock_sale.service.PermissionService;
import jakarta.validation.groups.Default;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/permission")
public class PermissionController {
    private final PermissionService permissionService;

    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }


    @PostMapping("/all")
    public ResponseEntity<List<PermissionResponseDTO>> createPermission(){
        List<PermissionResponseDTO> responseDTOList = permissionService.getPermissions();
        return ResponseEntity.ok().body(responseDTOList);
    }

    @PostMapping("/create")
    public ResponseEntity<PermissionResponseDTO> createPermission(
            @Validated({Default.class})
            @RequestBody PermissionRequestDTO requestDTO) {
        PermissionResponseDTO responseDTO = permissionService.createPermission(requestDTO);
        return ResponseEntity.ok().body(responseDTO);
    }
}
