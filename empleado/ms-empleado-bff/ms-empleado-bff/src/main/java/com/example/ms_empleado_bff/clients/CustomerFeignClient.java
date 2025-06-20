package com.example.ms_empleado_bff.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-customer-bff", url = "http://localhost:8282")
public interface CustomerFeignClient {
    @GetMapping("/api/customers")
    List<?> getAllClientes();

    @DeleteMapping("api/customers/{idCliente}")
    public void delete(@PathVariable("idCliente") Integer idCliente);

}
