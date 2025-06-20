package com.example.ms_producto_categoria_bff.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ms_producto_categoria_bff.modelDto.ProductoDTO;
import com.example.ms_producto_categoria_bff.service.ProductoService;


@RestController
@RequestMapping("/api/productos")
public class ProductoController {
    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public List<ProductoDTO> selectAllProducto() {
        return productoService.selectAllProducto();
    }

    @GetMapping("/{idProducto}")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ProductoDTO findById(@PathVariable Integer idProducto) {
        return productoService.findById(idProducto);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ProductoDTO save(@RequestBody ProductoDTO productoDTO) {
        return productoService.save(productoDTO);
    }

    @PutMapping("/{idProducto}")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ProductoDTO update(@PathVariable Integer idProducto, @RequestBody ProductoDTO productoDTO) {
        return productoService.update(idProducto, productoDTO);
    }

    @DeleteMapping("/{idProducto}")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public void delete(@PathVariable Integer idProducto) {
        productoService.delete(idProducto);
    }

    @PutMapping("/api/productos/{idProducto}/stock")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<ProductoDTO> actualizarStock(
            @PathVariable Integer idProducto,
            @RequestBody Integer nuevoStock) {
        ProductoDTO producto = productoService.actualizarStock(idProducto, nuevoStock);
        return ResponseEntity.ok(producto);
    }
}
