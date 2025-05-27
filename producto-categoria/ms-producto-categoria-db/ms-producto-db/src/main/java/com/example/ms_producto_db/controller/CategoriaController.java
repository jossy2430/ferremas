package com.example.ms_producto_db.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ms_producto_db.model.Categoria;
import com.example.ms_producto_db.service.CategoriaService;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {
    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<List<Categoria>> listar(){
        List<Categoria> categorias = categoriaService.findAll();
        if (categorias.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(categorias, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Categoria> guardar(@RequestBody Categoria categoria){
        Categoria nuevaCategoria = categoriaService.save(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaCategoria);
    }

    @GetMapping("/{idCategoria}")
    public ResponseEntity<Categoria> buscar(@PathVariable Integer idCategoria){
        try {
            Categoria categoria = categoriaService.findById(idCategoria);
            return ResponseEntity.ok(categoria);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{idCategoria}")
    public ResponseEntity<Categoria> actualizar(@PathVariable Integer idCategoria, @RequestBody Categoria categoria){
        try {
            Categoria cat = categoriaService.findById(idCategoria);
            cat.setIdCategoria(idCategoria);
            cat.setNombreCategoria(categoria.getNombreCategoria());

            categoriaService.save(cat);
            return ResponseEntity.ok(categoria);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{idCategoria}")
    public ResponseEntity<?> eliminar(@PathVariable Integer idCategoria){
        try {
            categoriaService.delete(idCategoria);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
        
    }


}
