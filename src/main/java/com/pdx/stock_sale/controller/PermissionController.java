package com.pdx.stock_sale.controller;

import com.pdx.stock_sale.dto.PermissionRequestDTO;
import com.pdx.stock_sale.dto.PermissionResponseDTO;
import com.pdx.stock_sale.dto.RoleRequestDTO;
import com.pdx.stock_sale.dto.RoleResponseDTO;
import com.pdx.stock_sale.service.PermissionService;
import jakarta.validation.groups.Default;
import lombok.RequiredArgsConstructor;
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

    @PostMapping("/")
    public ResponseEntity<List<PermissionResponseDTO>> getAll(){
        List<PermissionResponseDTO> response = permissionService.getPermissions();
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/{id}")
    public ResponseEntity<PermissionResponseDTO> getById(@PathVariable Integer id){
        PermissionResponseDTO response = permissionService.getPermissionById(id);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/create")
    public ResponseEntity<PermissionResponseDTO> create(
            @Validated({Default.class})
            @RequestBody PermissionRequestDTO requestDTO) {
        PermissionResponseDTO responseDTO = permissionService.createPermission(requestDTO);
        return ResponseEntity.ok().body(responseDTO);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<PermissionResponseDTO> update(
            @Validated({Default.class})
            @PathVariable Integer id,
            @RequestBody PermissionRequestDTO requestDTO) {
        PermissionResponseDTO responseDTO = permissionService.updatePermission(id,requestDTO);
        return ResponseEntity.ok().body(responseDTO);
    }
    @PostMapping("/delete/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Integer id) {
        permissionService.deletePermission(id);
        return ResponseEntity.noContent().build();
    }
}
