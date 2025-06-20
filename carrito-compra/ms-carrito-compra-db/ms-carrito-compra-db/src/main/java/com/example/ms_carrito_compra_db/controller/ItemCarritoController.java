package com.example.ms_carrito_compra_db.controller;

import java.util.List;

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

import com.example.ms_carrito_compra_db.model.ItemCarrito;
import com.example.ms_carrito_compra_db.service.ItemCarritoService;

@RestController
@RequestMapping("/api/itemcarrito")
public class ItemCarritoController {
    private final ItemCarritoService itemCarritoService;

    public ItemCarritoController(ItemCarritoService itemCarritoService) {
        this.itemCarritoService = itemCarritoService;
    }

    @GetMapping
    public ResponseEntity<List<ItemCarrito>> listar(){
        List<ItemCarrito> listaItemCarrito = itemCarritoService.findAll();
        if (listaItemCarrito.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(listaItemCarrito, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ItemCarrito> guardar(@RequestBody ItemCarrito itemCarrito){
        ItemCarrito nuevoItemCarrito = itemCarritoService.save(itemCarrito);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoItemCarrito);
    }

    @GetMapping("/{idItem}")
    public ResponseEntity<ItemCarrito> buscar (@PathVariable Integer idItem) {
        try {
            ItemCarrito itemCarrito = itemCarritoService.findById(idItem);
            return ResponseEntity.ok(itemCarrito);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{idItem}")
    public ResponseEntity<ItemCarrito> actualizar(@PathVariable Integer idItem, @RequestBody ItemCarrito itemCarrito) {
        try {
            ItemCarrito ic = itemCarritoService.findById(idItem);
            ic.setIdItem(idItem);
            ic.setIdProducto(itemCarrito.getIdProducto());
            ic.setCantidad(itemCarrito.getCantidad());
            ic.setPrecioUnitario(itemCarrito.getPrecioUnitario());
            ic.setSubTotal(itemCarrito.getSubTotal());
            
            itemCarritoService.save(ic);
            return ResponseEntity.ok(itemCarrito);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{idItem}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer idItem){
        try {
            itemCarritoService.delete(idItem);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
