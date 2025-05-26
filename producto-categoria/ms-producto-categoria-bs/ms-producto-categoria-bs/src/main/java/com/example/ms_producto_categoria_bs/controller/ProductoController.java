package com.example.ms_producto_categoria_bs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ms_producto_categoria_bs.modelDto.ProductoDTO;
import com.example.ms_producto_categoria_bs.service.ProductoService;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {
    @Autowired
    ProductoService productoService;

    @GetMapping
    public List<ProductoDTO> selectAllProducto() {
        return productoService.selectAllProducto();
    }

    @GetMapping("/{idProducto}")
    public ProductoDTO findById(@PathVariable Integer idProducto) {
        return productoService.findById(idProducto);
    }

    @PostMapping
    public ProductoDTO save(@RequestBody ProductoDTO productoDTO) {
        return productoService.save(productoDTO);
    }

    @PutMapping("/{idProducto}")
    public ProductoDTO update(@PathVariable Integer idProducto, @RequestBody ProductoDTO productoDTO) {
        return productoService.update(idProducto, productoDTO);
    }

    @DeleteMapping("/{idProducto}")
    public void delete(@PathVariable Integer idProducto) {
        productoService.delete(idProducto);
    }

}
