package com.example.ms_producto_categoria_bff.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.ms_producto_categoria_bff.modelDto.CategoriaDTO;

@FeignClient(name = "ms-categoria-bs", url = "http://localhost:8484/")
public interface CategoriaBsFeingClient {
    @GetMapping("api/categorias")
    public List<CategoriaDTO> selectAllCategoria();

    @GetMapping("api/categorias/{idCategoria}")
    public CategoriaDTO findById(@PathVariable("idCategoria") Integer idCategoria);

    @PostMapping("api/categorias")
    public CategoriaDTO save(@RequestBody CategoriaDTO categoriaDTO);

    @PutMapping("api/categorias/{idCategoria}")
    public CategoriaDTO update(@PathVariable("idCategoria") Integer idCategoria, @RequestBody CategoriaDTO categoriaDTO);

    @DeleteMapping("api/categorias/{idCategoria}")
    public void delete(@PathVariable("idCategoria") Integer idCategoria);

}
