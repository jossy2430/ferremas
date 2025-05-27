package com.example.ms_carrito_compra_bff.Controller;

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

import com.example.ms_carrito_compra_bff.modelDto.CarritoCompraDTO;
import com.example.ms_carrito_compra_bff.service.CarritoCompraService;

@RestController
@CrossOrigin(origins = "*")
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

}
