package com.example.ms_empleado_bff.controller;

import com.example.ms_empleado_bff.clients.EmpleadoBsfeignClient;
import com.example.ms_empleado_bff.modelDto.LoginRequest;
import com.example.ms_empleado_bff.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private EmpleadoBsfeignClient empleadoBsfeignClient;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginRequest loginRequest) {
        try {
            System.out.println("=== INICIO LOGIN ===");
            System.out.println("Usuario: " + loginRequest.getUsuario());
            System.out.println("Password: " + loginRequest.getPassword());
            
            // Crear mapa para enviar al BS
            Map<String, String> credenciales = new HashMap<>();
            credenciales.put("usuario", loginRequest.getUsuario());
            credenciales.put("password", loginRequest.getPassword());
            
            System.out.println("Enviando credenciales al BS: " + credenciales);
            
            // Llamar al BS - cambiar para usar Map en lugar de AuthResponse
            Map<String, Object> bsResponse = empleadoBsfeignClient.validarUsuario(credenciales);
            System.out.println("Respuesta del BS: " + bsResponse);
            
            // Extraer valores del Map (BS devuelve "autenticado" y "token")
            Boolean autenticado = (Boolean) bsResponse.get("autenticado");
            String rol = (String) bsResponse.get("token"); // El BS envía el rol en "token"
            
            System.out.println("Autenticado: " + autenticado);
            System.out.println("Rol extraído: " + rol);
            
            if (autenticado != null && autenticado) {
                // Generar JWT token para el BFF
                String jwtToken = jwtUtil.generateToken(loginRequest.getUsuario(), rol);
                
                Map<String, Object> response = new HashMap<>();
                response.put("success", true);
                response.put("message", "Login exitoso");
                response.put("token", jwtToken);
                response.put("usuario", loginRequest.getUsuario());
                response.put("rol", rol);
                
                System.out.println("=== LOGIN EXITOSO ===");
                System.out.println("JWT Token generado: " + jwtToken);
                return ResponseEntity.ok(response);
            } else {
                System.out.println("=== LOGIN FALLIDO ===");
                Map<String, Object> response = new HashMap<>();
                response.put("success", false);
                response.put("message", "Credenciales inválidas - Usuario o contraseña incorrectos");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
        } catch (Exception e) {
            System.out.println("=== ERROR EN LOGIN ===");
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Error de comunicación con el servidor");
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}