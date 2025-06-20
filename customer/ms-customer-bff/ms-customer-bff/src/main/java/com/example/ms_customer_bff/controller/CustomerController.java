package com.example.ms_customer_bff.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.ms_customer_bff.clients.CustomerDbFeignClient;
import com.example.ms_customer_bff.modelDTO.CustomerDTO;
import com.example.ms_customer_bff.service.AuthService;
import com.example.ms_customer_bff.service.CustomerService;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @Autowired
    AuthService authService;

    @Autowired
    private CustomerDbFeignClient customerDbFeignClient;

    @GetMapping
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public List<CustomerDTO> selectAllCustomer() {
        return customerService.selectAllCustomer();
    }

    @GetMapping("/{idCliente}")
    public CustomerDTO findById(@PathVariable Integer idCliente) {
        return customerService.findById(idCliente);
    }

    @PostMapping
    public CustomerDTO create(@RequestBody CustomerDTO customerDTO) {
        return customerService.save(customerDTO);
    }

    @PutMapping("/{idCliente}")
    public ResponseEntity<CustomerDTO> update(@PathVariable Integer idCliente, @RequestBody CustomerDTO customerDTO, Principal principal) {
        System.out.println("Correo del token: " + principal.getName());
        System.out.println("Correo del body: " + customerDTO.getCorreo());
        if (!principal.getName().equalsIgnoreCase(customerDTO.getCorreo())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        CustomerDTO actualizado = authService.update(idCliente, customerDTO);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{idCliente}")
    public void delete(@PathVariable Integer idCliente) {
        customerService.delete(idCliente);
    }

}
