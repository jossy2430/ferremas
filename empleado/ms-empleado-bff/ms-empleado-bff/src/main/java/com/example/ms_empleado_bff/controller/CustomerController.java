package com.example.ms_empleado_bff.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ms_empleado_bff.clients.CustomerFeignClient;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    @Autowired
    private CustomerFeignClient customerFeignClient;

    @GetMapping
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<?> listarClientes(){
        return ResponseEntity.ok(customerFeignClient.getAllClientes());
    }

    @DeleteMapping("/{idCliente}")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<?> eliminarCliente(@PathVariable Integer idCliente){
        customerFeignClient.delete(idCliente);
        return ResponseEntity.noContent().build();
    }


}
