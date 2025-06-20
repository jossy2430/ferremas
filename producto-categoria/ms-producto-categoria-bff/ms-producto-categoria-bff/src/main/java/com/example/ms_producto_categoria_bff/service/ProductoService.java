package com.example.ms_producto_categoria_bff.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ms_producto_categoria_bff.clients.CategoriaBsFeingClient;
import com.example.ms_producto_categoria_bff.clients.ProductoBsFeignClient;
import com.example.ms_producto_categoria_bff.modelDto.CategoriaDTO;
import com.example.ms_producto_categoria_bff.modelDto.ProductoDTO;

@Service
public class ProductoService {
    @Autowired
    ProductoBsFeignClient productoBsFeignClient;

    @Autowired
    CategoriaBsFeingClient categoriaBsFeingClient;

    public ProductoService(ProductoBsFeignClient productoBsFeignClient){
        this.productoBsFeignClient = productoBsFeignClient;
    }

    public List<ProductoDTO> selectAllProducto(){
        return productoBsFeignClient.selectAllProducto();
    }

    public ProductoDTO findById(Integer idProducto){
        return productoBsFeignClient.findById(idProducto);
    }

    public ProductoDTO save(ProductoDTO productoDTO) {
        // Validar nombre
        if (productoDTO.getNombre() == null || productoDTO.getNombre().isEmpty()) {
            throw new IllegalArgumentException("El nombre del producto no puede estar vacío.");
        }

        // Validar descripción
        if (productoDTO.getDescripcion() == null || productoDTO.getDescripcion().isEmpty()) {
            throw new IllegalArgumentException("La descripción del producto no puede estar vacía.");
        }
        if (productoDTO.getDescripcion().length() > 200) {
            throw new IllegalArgumentException("La descripción del producto no puede exceder los 200 caracteres.");
        }

        // Validar precio
        if (productoDTO.getPrecio() == null || productoDTO.getPrecio() <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor que cero.");
        }

        // Validar stock
        if (productoDTO.getStock() == null || productoDTO.getStock() < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo.");
        }

        // Validar imagenURL
        if (productoDTO.getImagenURL() == null || !isValidURL(productoDTO.getImagenURL())) {
            throw new IllegalArgumentException("La URL de la imagen no es válida.");
        }

        // Validar categoría
        if (productoDTO.getCategoria() == null || !categoriaExiste(productoDTO.getCategoria().getIdCategoria())) {
            throw new IllegalArgumentException("La categoría especificada no es válida o no existe.");
        }

        return productoBsFeignClient.save(productoDTO);
    }

    public ProductoDTO update(Integer idProducto, ProductoDTO productoDTO){
        if (!isValidURL(productoDTO.getImagenURL())) {
            throw new IllegalArgumentException("La URL de la imagen no es válida.");
        }
        return productoBsFeignClient.update(idProducto, productoDTO);
    }

    public void delete(Integer idProducto){
        productoBsFeignClient.delete(idProducto);
    }

    public ProductoDTO actualizarStock(Integer idProducto, Integer nuevoStock) {
        return productoBsFeignClient.actualizarStock(idProducto, nuevoStock);
    }

    private boolean isValidURL(String url){
        try {
            new java.net.URL(url).toURI();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean categoriaExiste(Integer idCategoria) {
        try {
            CategoriaDTO categoria = categoriaBsFeingClient.findById(idCategoria);
            return categoria != null;
        } catch (Exception e) {
            return false;
        }
    }
}


