package com.example.ms_customer_bff.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.ms_customer_bff.modelDTO.CustomerDTO;


@FeignClient(name = "ms-customer-bs", url = "http://localhost:8181/")
public interface CustomerDbFeignClient {
    @GetMapping("api/customers")
    public List<CustomerDTO> selectAllCustomer();

    @GetMapping("api/customers/{idCliente}")
    public CustomerDTO findById(@PathVariable("idCliente") Integer idCliente);

    @PostMapping("api/customers")
    public CustomerDTO save(@RequestBody CustomerDTO customerDTO);

    @PutMapping("api/customers/{idCliente}")
    public CustomerDTO update(@PathVariable("idCliente") Integer idCliente, @RequestBody CustomerDTO customerDTO);

    @DeleteMapping("api/customers/{idCliente}")
    public void delete(@PathVariable("idCliente") Integer idCliente);

    @GetMapping("api/customers/correo/{correo}")
    public CustomerDTO findByCorreo(@PathVariable("correo") String correo);
}
