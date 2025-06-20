package com.example.ms_carrito_compra_bff.Controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ms_carrito_compra_bff.security.JwtUtil;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/auth")
public class AuthController {
    
    private final JwtUtil jwtUtil;

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");
        
        if (validateCredentials(username, password)) {
            String role = getUserRole(username);
            String token = jwtUtil.generateToken(username, role);
            
            return ResponseEntity.ok(Map.of(
                "success", true,
                "token", token,
                "role", role,
                "username", username,
                "message", "Login exitoso"
            ));
        } else {
            return ResponseEntity.status(401).body(Map.of(
                "success", false,
                "message", "Credenciales inválidas"
            ));
        }
    }
    
    private boolean validateCredentials(String username, String password) {
        return ("admin".equals(username) && "admin123".equals(password)) ||
               ("cliente".equals(username) && "cliente123".equals(password)) ||
               ("vendedor".equals(username) && "vendedor123".equals(password)) ||
               ("bodeguero".equals(username) && "bodeguero123".equals(password));
    }
    
    private String getUserRole(String username) {
        return switch (username) {
            case "admin" -> "ADMIN";
            case "cliente" -> "CLIENTE";
            case "vendedor" -> "VENDEDOR";
            case "bodeguero" -> "BODEGUERO";
            default -> "CLIENTE";
        };
    }
}