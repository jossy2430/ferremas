package com.example.ms_producto_categoria_bs.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.ms_producto_categoria_bs.modelDto.ProductoDTO;

@FeignClient(name = "ms-producto-db", url = "http://localhost:8080/")
public interface ProductoDbFeignClient {

    @GetMapping("api/productos")
    public List<ProductoDTO> selectAllProducto();

    @GetMapping("api/productos/{idProducto}")
    public ProductoDTO findById(@PathVariable("idProducto") Integer idProducto);

    @PostMapping("api/productos")
    public ProductoDTO save(@RequestBody ProductoDTO productoDTO);

    @PutMapping("api/productos{idProducto}")
    public ProductoDTO update(@PathVariable("idProducto") Integer idProducto, @RequestBody ProductoDTO productoDTO);

    @DeleteMapping("api/productos/{idProducto}")
    public void delete(@PathVariable("idProducto") Integer idProducto);

}
