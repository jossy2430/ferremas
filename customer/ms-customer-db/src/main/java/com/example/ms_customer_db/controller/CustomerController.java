package com.example.ms_customer_db.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ms_customer_db.model.Customer;
import com.example.ms_customer_db.service.CustomerService;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<Customer>> listar(){
        List<Customer> customers = customerService.findAll();
        if (customers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Customer> guardar(@RequestBody Customer customer){
        Customer nuevoCustomer = customerService.save(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCustomer);
    }

    @GetMapping("/{idCliente}")
    public ResponseEntity<Customer> buscar(@PathVariable Integer idCliente){
        try {
            Customer customer = customerService.findById(idCliente);
            return ResponseEntity.ok(customer);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{idCliente}")
    public ResponseEntity<Customer> actualizar(@PathVariable Integer idCliente, @RequestBody Customer customer){
        try {
            Customer cus = customerService.findById(idCliente);
            cus.setNombres(customer.getNombres());
            cus.setApellidos(customer.getApellidos());
            cus.setRut(customer.getRut());
            cus.setCorreo(customer.getCorreo());
            cus.setPassword(customer.getPassword());
            cus.setFechaResgistro(customer.getFechaResgistro());
            cus.setRecibirNotificacion(customer.getRecibirNotificacion());
            cus.setDireccion(customer.getDireccion());
            cus.setTelefono(customer.getTelefono());

            customerService.save(cus);
            return ResponseEntity.ok(customer);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{idCliente}")
    public ResponseEntity<?> eliminar(@PathVariable Integer idCliente){
        try {
            customerService.delete(idCliente);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/correo/{correo}")
    public ResponseEntity<Customer> findByCorreo(@PathVariable String correo){
        Optional<Customer> cliente = customerService.findByCorreo(correo);
        return cliente.map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());

    }
}
