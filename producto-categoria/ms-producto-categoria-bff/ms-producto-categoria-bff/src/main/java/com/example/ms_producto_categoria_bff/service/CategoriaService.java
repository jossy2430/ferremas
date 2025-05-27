package com.example.ms_producto_categoria_bff.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ms_producto_categoria_bff.clients.CategoriaBsFeingClient;
import com.example.ms_producto_categoria_bff.modelDto.CategoriaDTO;

@Service
public class CategoriaService {
    @Autowired
    CategoriaBsFeingClient categoriaBsFeingClient;

    public List<CategoriaDTO> selectAllCategoria() {
        return categoriaBsFeingClient.selectAllCategoria();
    }

    public CategoriaDTO findById(Integer idCategoria) {
        return categoriaBsFeingClient.findById(idCategoria);
    }

    public CategoriaDTO save(CategoriaDTO categoriaDTO) {
        return categoriaBsFeingClient.save(categoriaDTO);
    }

    public CategoriaDTO update(Integer idCategoria, CategoriaDTO categoriaDTO) {
        return categoriaBsFeingClient.update(idCategoria, categoriaDTO);
    }

    public void delete(Integer idCategoria) {
        categoriaBsFeingClient.delete(idCategoria);
    }

}
