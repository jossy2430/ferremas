package com.example.ms_customer_bff.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ms_customer_bff.modelDto.CustomerDTO;
import com.example.ms_customer_bff.service.CustomerService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/customers")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @GetMapping
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
    public CustomerDTO update(@PathVariable Integer idCliente, @RequestBody CustomerDTO customerDTO) {
        return customerService.update(idCliente, customerDTO);
    }

    @DeleteMapping("/{idCliente}")
    public void delete(@PathVariable Integer idCliente) {
        customerService.delete(idCliente);
    }

}
