package com.example.ms_customer_bff.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.ms_customer_bff.clients.CustomerDbFeignClient;
import com.example.ms_customer_bff.modelDTO.CustomerDTO;

import feign.FeignException;

@Service
public class AuthService {

    @Autowired
    private CustomerDbFeignClient customerDbFeignClient;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CustomerDTO registro(CustomerDTO customerDTO){
        CustomerDTO clienteExiste = null;
        try {
            clienteExiste = customerDbFeignClient.findByCorreo(customerDTO.getCorreo());
        } catch (FeignException.NotFound e) {
            // no existe, se puede registrar
        }
        if (clienteExiste != null) {
            throw new RuntimeException("cliente existe");
        } 

        //codificar contraseña
        customerDTO.setPassword(passwordEncoder.encode(customerDTO.getPassword()));
        return customerDbFeignClient.save(customerDTO);
    }

    public CustomerDTO authenticate(String correo, String password){
        CustomerDTO cliente = customerDbFeignClient.findByCorreo(correo);
        if (cliente == null ) {
            throw new RuntimeException("cliente no encontrado");
        }
        if (!passwordEncoder.matches(password,cliente.getPassword())) {
            throw new RuntimeException("contraseña incorrecta");
        }
        return cliente;
    }

    public CustomerDTO update(Integer idCliente, CustomerDTO customerDTO){
        return customerDbFeignClient.update(idCliente, customerDTO);
    }

}
