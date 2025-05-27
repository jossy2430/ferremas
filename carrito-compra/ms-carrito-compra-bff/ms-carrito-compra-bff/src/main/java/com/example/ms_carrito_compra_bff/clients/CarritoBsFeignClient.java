package com.example.ms_carrito_compra_bff.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.ms_carrito_compra_bff.modelDto.CarritoCompraDTO;

@FeignClient(name = "ms-carrito-compra-bs",url = "http://localhost:8181/")
public interface CarritoBsFeignClient {
    @GetMapping("api/carrito")
    public List<CarritoCompraDTO> selectAllCarritoCompra();

    @GetMapping("api/carrito/{idCarrito}")
    public CarritoCompraDTO findById(@PathVariable("idCarrito") Integer idCarrito);

    @PostMapping("api/carrito")
    public CarritoCompraDTO save(CarritoCompraDTO carritoCompraDTO);

    @PutMapping("api/carrito/{idCarrito}")
    public CarritoCompraDTO actualizar(@PathVariable("idCarrito") Integer idCarrito,@RequestBody CarritoCompraDTO carritoCompraDTO);

    @DeleteMapping("api/carrito/{idCarrito}")
    public void delete(@PathVariable("idCarrito") Integer idCarrito);
}
