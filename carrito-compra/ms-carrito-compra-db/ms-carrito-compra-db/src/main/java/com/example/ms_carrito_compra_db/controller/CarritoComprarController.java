package com.example.ms_carrito_compra_db.controller;

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

import com.example.ms_carrito_compra_db.model.CarritoCompra;
import com.example.ms_carrito_compra_db.service.CarritoCompraService;

@RestController
@RequestMapping("api/carrito")
public class CarritoComprarController {
    @Autowired
    private CarritoCompraService carritoCompraService;

    @GetMapping
    public ResponseEntity<List<CarritoCompra>> listar(){
        List<CarritoCompra> listaCarrito = carritoCompraService.findAll();
        if (listaCarrito.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(listaCarrito, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CarritoCompra> guardar(@RequestBody CarritoCompra carritoCompra){
        CarritoCompra nuevoCarrito = carritoCompraService.save(carritoCompra);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCarrito);
    }

    @GetMapping("/{idCarrito}")
    public ResponseEntity<CarritoCompra> buscar(@PathVariable Integer idCarrito){
        try {
            CarritoCompra carritoCompra = carritoCompraService.findById(idCarrito);
            return ResponseEntity.ok(carritoCompra);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{idCarrito}")
    public ResponseEntity<CarritoCompra> actualizar(@PathVariable Integer idCarrito, @RequestBody CarritoCompra carritoCompra){
        try {
            CarritoCompra cc = carritoCompraService.findById(idCarrito);
            cc.setIdCarrito(idCarrito);
            cc.setIdCliente(carritoCompra.getIdCliente());
            cc.setItems(carritoCompra.getItems());
            cc.setFechaCreacion(carritoCompra.getFechaCreacion());
            cc.setActivo(carritoCompra.getActivo());
            cc.setTotal(carritoCompra.getTotal());

            carritoCompraService.save(cc);
            return ResponseEntity.ok(carritoCompra);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{idCarrito}")
    public ResponseEntity<?> eliminar(@PathVariable Integer idCarrito){
        try {
            carritoCompraService.delete(idCarrito);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
