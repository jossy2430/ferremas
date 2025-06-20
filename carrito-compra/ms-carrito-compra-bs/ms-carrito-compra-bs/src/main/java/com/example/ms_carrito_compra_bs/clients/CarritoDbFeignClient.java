package com.example.ms_carrito_compra_bs.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.ms_carrito_compra_bs.modelDto.CarritoCompraDTO;

@FeignClient(name = "ms-carrito-compra-db", url = "http://localhost:8686/")
public interface CarritoDbFeignClient {

    @GetMapping("api/carrito")
    List<CarritoCompraDTO> selectAllCarritoCompra();

    @GetMapping("api/carrito/{idCarrito}")
    CarritoCompraDTO findById(@PathVariable("idCarrito") Integer idCarrito);

    @PostMapping("api/carrito")
    CarritoCompraDTO save(@RequestBody CarritoCompraDTO carritoCompraDTO);

    @PutMapping("api/carrito/{idCarrito}")
    CarritoCompraDTO actualizar(@PathVariable("idCarrito") Integer idCarrito, @RequestBody CarritoCompraDTO carritoCompraDTO);

    @DeleteMapping("api/carrito/{idCarrito}")
    void delete(@PathVariable("idCarrito") Integer idCarrito);

    @GetMapping("api/carrito/cliente/{idCliente}")
    CarritoCompraDTO findByIdCliente(@PathVariable("idCliente") Integer idCliente);
}
