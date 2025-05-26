package com.example.ms_producto_categoria_bs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ms_producto_categoria_bs.clients.ProductoDbFeignClient;
import com.example.ms_producto_categoria_bs.modelDto.ProductoDTO;

@Service
public class ProductoService {
    @Autowired
    ProductoDbFeignClient productoDbFeignClient;

    public List<ProductoDTO> selectAllProducto(){
        return productoDbFeignClient.selectAllProducto();
    }

    public ProductoDTO findById(Integer idProducto){
        return productoDbFeignClient.findById(idProducto);
    }

    public ProductoDTO save(ProductoDTO productoDTO){
        return productoDbFeignClient.save(productoDTO);
    }

    public ProductoDTO update(Integer idProducto, ProductoDTO productoDTO){
        return productoDbFeignClient.update(idProducto, productoDTO);
    }

    public void delete(Integer idProducto){
        productoDbFeignClient.delete(idProducto);
    }

}
