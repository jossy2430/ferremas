package com.example.ms_carrito_compra_bff.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.ms_carrito_compra_bff.modelDto.CarritoCompraDTO;

@FeignClient(name = "ms-carrito-compra-bs", url = "http://localhost:8787/")
public interface CarritoBsFeignClient {
    
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

    @PostMapping("api/carrito/{idCarrito}/agregar-productos")
    CarritoCompraDTO agregarProductos(
        @PathVariable("idCarrito") Integer idCarrito,
        @RequestParam Integer idProducto,
        @RequestParam Integer cantidad,
        @RequestParam Double precioUnitario,
        @RequestParam(required = false) Double descuento
    );

    @DeleteMapping("api/carrito/{idCarrito}/eliminar-productos/{idProducto}")
    CarritoCompraDTO eliminarProducto(
        @PathVariable("idCarrito") Integer idCarrito,
        @PathVariable("idProducto") Integer idProducto
    );

    @PostMapping("api/carrito/{idCarrito}/vaciar")
    CarritoCompraDTO vaciarCarrito(@PathVariable("idCarrito")Integer idCarrito);
    
}
