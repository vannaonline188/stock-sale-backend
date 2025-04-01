package com.pdx.stock_sale.controller;

import com.pdx.stock_sale.dto.RoleRequestDTO;
import com.pdx.stock_sale.dto.RoleResponseDTO;
import com.pdx.stock_sale.dto.UserRequestDTO;
import com.pdx.stock_sale.dto.UserResponseDTO;
import com.pdx.stock_sale.service.UserService;
import jakarta.validation.groups.Default;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;
    @PostMapping("/")
    public ResponseEntity<List<UserResponseDTO>> Users(){
        List<UserResponseDTO> response = userService.getUsers();
        return ResponseEntity.ok().body(response);
    }
    @PostMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getById(@PathVariable Integer id){
        UserResponseDTO response = userService.getUserById(id);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/create")
    public ResponseEntity<UserResponseDTO> createUser(
            @Validated({Default.class})
            @RequestBody UserRequestDTO requestDTO) {
        UserResponseDTO responseDTO = userService.createUser(requestDTO);
        return ResponseEntity.ok().body(responseDTO);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(
            @Validated({Default.class})
            @PathVariable Integer id,
            @RequestBody UserRequestDTO requestDTO) {
        UserResponseDTO responseDTO = userService.updateUser(id,requestDTO);
        return ResponseEntity.ok().body(responseDTO);
    }

}
