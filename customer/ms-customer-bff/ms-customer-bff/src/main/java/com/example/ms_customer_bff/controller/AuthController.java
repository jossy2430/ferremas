package com.example.ms_customer_bff.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ms_customer_bff.modelDTO.CustomerDTO;
import com.example.ms_customer_bff.modelDTO.LoginRequest;
import com.example.ms_customer_bff.service.AuthService;
import com.example.ms_customer_bff.service.JwtService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/registro")
    public ResponseEntity<?> registro(@RequestBody CustomerDTO customerDTO){
        CustomerDTO nuevoCliente = authService.registro(customerDTO);
        return ResponseEntity.ok(nuevoCliente);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        CustomerDTO customerDTO = authService.authenticate(loginRequest.getCorreo(), loginRequest.getPassword());
        String token = jwtService.generateToken(customerDTO);
        return ResponseEntity.ok(Map.of("token",token));
    }

}
