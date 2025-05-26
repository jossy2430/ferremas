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

import com.example.ms_producto_categoria_bs.modelDto.CategoriaDTO;
import com.example.ms_producto_categoria_bs.service.CategoriaService;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {
    @Autowired
    CategoriaService categoriaService;

    @GetMapping
    public List<CategoriaDTO> selectAllCategoria(){
        return categoriaService.selectAllCategoria();
    }

    @GetMapping("/{idCategoria}")
    public CategoriaDTO findById(@PathVariable Integer idCategoria){
        return categoriaService.findById(idCategoria);
    }

    @PostMapping
    public CategoriaDTO save(@RequestBody CategoriaDTO categoriaDTO){
        return categoriaService.save(categoriaDTO);
    }

    @PutMapping("/{idCategoria}")
    public CategoriaDTO update(@PathVariable Integer idCategoria, @RequestBody CategoriaDTO categoriaDTO){
        return categoriaService.update(idCategoria, categoriaDTO);
    }

    @DeleteMapping("/{idCategoria}")
    public void delete(@PathVariable Integer idCategoria){
        categoriaService.delete(idCategoria);
    }
}
