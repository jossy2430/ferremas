package com.ferremas.integracion.de.plataformas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ferremas.integracion.de.plataformas.model.Cliente;
import com.ferremas.integracion.de.plataformas.service.ClienteService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/api/vi1/clientes")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<Cliente>> listar(){
        List<Cliente> clientes = clienteService.findAll();
        if (clientes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Cliente> guardar(@RequestBody Cliente cliente){
        Cliente clienteNuevo = clienteService.save(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteNuevo);
    }
    
    @GetMapping("/{idcliente}")
    public ResponseEntity<Cliente> buscar(@PathVariable Integer idcliente) {
        try {
            Cliente cliente = clienteService.findById(idcliente);
            return ResponseEntity.ok(cliente);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PutMapping("/{idcliente}")
    public ResponseEntity<Cliente> actualizar(@PathVariable Integer idcliente, @RequestBody Cliente cliente){
        try {
            Cliente clie = clienteService.findById(idcliente);
            clie.setIdcliente(idcliente);
            clie.setRut(cliente.getRut());
            clie.setNombres(cliente.getNombres());
            clie.setApellidos(cliente.getApellidos());
            clie.setCorreo(cliente.getCorreo());
            clie.setPassword(cliente.getPassword());
            clie.setFecharegistro(cliente.getFecharegistro());
            clie.setRecibenotificacion(cliente.getRecibenotificacion());
            clie.setDireccioncliente(cliente.getDireccioncliente());
            clie.setTelefono(cliente.getTelefono());
            return ResponseEntity.ok(cliente);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{idcliente}")
    public ResponseEntity<?> eliminar(@PathVariable Long idcliente){
        try {
            clienteService.delete(idcliente);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
