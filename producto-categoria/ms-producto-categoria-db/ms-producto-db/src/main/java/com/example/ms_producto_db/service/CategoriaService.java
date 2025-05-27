package com.example.ms_producto_db.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ms_producto_db.model.Categoria;
import com.example.ms_producto_db.repository.CategoriaRepository;

@Service
@Transactional
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> findAll(){
        return categoriaRepository.findAll();
    }

    public Categoria findById(Integer idCategoria){
        return categoriaRepository.findById(idCategoria).orElse(null);
    }

    public Categoria save(Categoria categoria){
        return categoriaRepository.save(categoria);
    }

    public void delete(Integer idCategoria){
        categoriaRepository.deleteById(idCategoria);
    }
}