package com.example.ms_producto_categoria_bs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ms_producto_categoria_bs.clients.CategoriaDbFeignClient;
import com.example.ms_producto_categoria_bs.modelDto.CategoriaDTO;

@Service
public class CategoriaService {
    @Autowired
    CategoriaDbFeignClient categoriaDbFeignClient;

    public List<CategoriaDTO> selectAllCategoria() {
        return categoriaDbFeignClient.selectAllCategoria();
    }

    public CategoriaDTO findById(Integer idCategoria) {
        return categoriaDbFeignClient.findById(idCategoria);
    }

    public CategoriaDTO save(CategoriaDTO categoriaDTO) {
        return categoriaDbFeignClient.save(categoriaDTO);
    }

    public CategoriaDTO update(Integer idCategoria, CategoriaDTO categoriaDTO) {
        return categoriaDbFeignClient.update(idCategoria, categoriaDTO);
    }

    public void delete(Integer idCategoria) {
        categoriaDbFeignClient.delete(idCategoria);
    }
}
