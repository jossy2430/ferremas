package com.example.ms_customer_bs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ms_customer_bs.clients.CustomerDbFeignClient;
import com.example.ms_customer_bs.modelDto.CustomerDTO;

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

    public CustomerDTO findByCorreo(String correo){
        try {
            return customerDbFeignClient.findByCorreo(correo);
        } catch (FeignException.NotFound e) {
            return null;
        }
    }
}
