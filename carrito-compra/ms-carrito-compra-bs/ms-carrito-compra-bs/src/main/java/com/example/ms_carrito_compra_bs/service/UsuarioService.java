package com.example.ms_carrito_compra_bs.service;

import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    
    public boolean validarUsuario(String username, String password) {
        
        return "admin".equals(username) && "admin123".equals(password);
    }

    public String obtenerRol(String username) {
        
        if ("admin".equals(username)) return "ADMIN";
        return "CLIENTE";
    }

}
