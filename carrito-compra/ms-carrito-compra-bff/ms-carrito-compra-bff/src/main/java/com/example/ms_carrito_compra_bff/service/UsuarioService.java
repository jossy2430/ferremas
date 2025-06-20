package com.example.ms_carrito_compra_bff.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UsuarioService {
    
    private final RestTemplate restTemplate;

    public UsuarioService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Integer getClienteIdByUsername(String username) {
        try {
            // Llamada al microservicio de customers para obtener el ID del cliente
            String url = "http://localhost:8484/api/customers/username/" + username;
            var response = restTemplate.getForObject(url, java.util.Map.class);
            if (response != null && response.get("idCliente") != null) {
                Object idObject = response.get("idCliente");
                if (idObject instanceof Integer) {
                    return (Integer) idObject;
                } 
                if (idObject instanceof Number) {
                    return ((Number) idObject).intValue();
                }
            }
            
            return null;
        } catch (Exception e) {
            return null;
        }
    }
}