package com.example.ms_producto_categoria_bff.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ms_producto_categoria_bff.clients.ProductoBsFeignClient;
import com.example.ms_producto_categoria_bff.modelDto.ProductoDTO;

@Service
public class ProductoService {
    @Autowired
    ProductoBsFeignClient productoBsFeignClient;
    public List<ProductoDTO> selectAllProducto(){
        return productoBsFeignClient.selectAllProducto();
    }

    public ProductoDTO findById(Integer idProducto){
        return productoBsFeignClient.findById(idProducto);
    }

    public ProductoDTO save(ProductoDTO productoDTO){
        return productoBsFeignClient.save(productoDTO);
    }

    public ProductoDTO update(Integer idProducto, ProductoDTO productoDTO){
        return productoBsFeignClient.update(idProducto, productoDTO);
    }

    public void delete(Integer idProducto){
        productoBsFeignClient.delete(idProducto);
    }

}
