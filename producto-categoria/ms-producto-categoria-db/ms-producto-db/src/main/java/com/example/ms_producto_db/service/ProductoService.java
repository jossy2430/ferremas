package com.example.ms_producto_db.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.example.ms_producto_db.model.Producto;
import com.example.ms_producto_db.repository.ProductoRepository;


@Service
@Transactional
public class ProductoService {
    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> findAll(){
        return productoRepository.findAll();
    }

    public Producto findById(Integer idProducto){
        return productoRepository.findById(idProducto).get();
    }

    public Producto save(Producto producto){
        return productoRepository.save(producto);
    }

    public void delete(Integer idProducto){
        productoRepository.deleteById(idProducto);
    }

    //actualizar el stock de un producto
    public Producto actualizarStock(Integer idProducto, Integer nuevoStock){
        Producto producto = productoRepository.findById(idProducto).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        producto.setStock(nuevoStock);
        return productoRepository.save(producto);
    }
}
