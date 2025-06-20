package com.example.ms_carrito_compra_bs.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ms_carrito_compra_bs.service.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/validar")
    public Map<String, Object> validarUsuario(@RequestBody Map<String, String> body){
        String username = body.get("username");
        String password = body.get("password");

        boolean valido = usuarioService.validarUsuario(username, password);
        String rol = valido ? usuarioService.obtenerRol(username): null;

        return Map.of(
            "valido", valido,
            "rol", rol
        );
    }

}
