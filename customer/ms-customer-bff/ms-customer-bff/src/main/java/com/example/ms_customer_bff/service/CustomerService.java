package com.example.ms_customer_bff.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ms_customer_bff.clients.CustomerDbFeignClient;
import com.example.ms_customer_bff.modelDTO.CustomerDTO;

import feign.FeignException;

@Service
public class CustomerService {
    @Autowired
    CustomerDbFeignClient customerDbFeignClient;

    public List<CustomerDTO> selectAllCustomer() {
        return customerDbFeignClient.selectAllCustomer();
    }

    public CustomerDTO findById(Integer idCliente) {
        return customerDbFeignClient.findById(idCliente);
    }

    public CustomerDTO save(CustomerDTO customerDTO) {
        return customerDbFeignClient.save(customerDTO);
    }

    public CustomerDTO update(Integer idCliente, CustomerDTO customerDTO) {
        return customerDbFeignClient.update(idCliente, customerDTO);
    }

    public void delete(Integer idCliente) {
        customerDbFeignClient.delete(idCliente);
    }

    public CustomerDTO registro(CustomerDTO customerDTO) {
        CustomerDTO existente = null;
        try {
            existente = customerDbFeignClient.findByCorreo(customerDTO.getCorreo());
        } catch (FeignException.NotFound e) {
        // No existe, es correcto
        }
        if (existente != null) {
            throw new RuntimeException("El correo ya está registrado");
        }
        return customerDbFeignClient.save(customerDTO);
    }

}
