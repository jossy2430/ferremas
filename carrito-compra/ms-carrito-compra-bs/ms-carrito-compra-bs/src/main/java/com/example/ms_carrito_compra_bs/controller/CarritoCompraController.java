package com.example.ms_carrito_compra_bs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ms_carrito_compra_bs.modelDto.CarritoCompraDTO;
import com.example.ms_carrito_compra_bs.service.CarritoCompraService;

@RestController
@RequestMapping("/api/carrito")
public class CarritoCompraController {
    @Autowired
    CarritoCompraService carritoCompraService;

    @GetMapping
    public List<CarritoCompraDTO> selectAllCarritoCompra() {
        return carritoCompraService.selectAllCarritoCompra();
    }

    @GetMapping("/{idCarrito}")
    public CarritoCompraDTO findById(@PathVariable Integer idCarrito) {
        return carritoCompraService.findById(idCarrito);
    }

    @PostMapping
    public CarritoCompraDTO save(@RequestBody CarritoCompraDTO carritoCompraDTO) {
        return carritoCompraService.save(carritoCompraDTO);
    }

    @PutMapping("/{idCarrito}")
    public CarritoCompraDTO actualizar(@PathVariable Integer idCarrito, @RequestBody CarritoCompraDTO carritoCompraDTO) {
        return carritoCompraService.actualizar(idCarrito, carritoCompraDTO);
    }

    @DeleteMapping("/{idCarrito}")
    public void delete(@PathVariable Integer idCarrito) {
        carritoCompraService.delete(idCarrito);
    }

    @PostMapping("/{idCarrito}/agregar-productos")
    public ResponseEntity<CarritoCompraDTO> agregarProducto(
            @PathVariable Integer idCarrito,
            @RequestParam Integer idProducto,
            @RequestParam Integer cantidad,
            @RequestParam Double precioUnitario,
            @RequestParam(required = false) Double descuento) {
        CarritoCompraDTO carrito = carritoCompraService.agregarProducto(idCarrito, idProducto, cantidad, precioUnitario, descuento);
        return ResponseEntity.ok(carrito);
    }

    @DeleteMapping("/{idCarrito}/eliminar-productos/{idProducto}")
    public ResponseEntity<CarritoCompraDTO> eliminarProducto(
            @PathVariable Integer idCarrito,
            @PathVariable Integer idProducto) {
        CarritoCompraDTO carrito = carritoCompraService.eliminarProducto(idCarrito, idProducto);
        return ResponseEntity.ok(carrito);
    }

    @PostMapping("/{idCarrito}/vaciar")
    public ResponseEntity<CarritoCompraDTO> vaciarCarrito(@PathVariable Integer idCarrito) {
        CarritoCompraDTO carrito = carritoCompraService.vaciarCarrito(idCarrito);
        return ResponseEntity.ok(carrito);
    }

}
