package com.tacosmanager.controller;

import com.tacosmanager.dto.LoginDTO;
import com.tacosmanager.dto.ProduitDTO;
import com.tacosmanager.dto.UserDTO;
import com.tacosmanager.entity.Produit;
import com.tacosmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping()
    public ResponseEntity<UserDTO> login(@RequestBody LoginDTO loginDTO) {
            UserDTO userDTO = userService.authenticate(loginDTO);
            return ResponseEntity.ok(userDTO);

    }

}