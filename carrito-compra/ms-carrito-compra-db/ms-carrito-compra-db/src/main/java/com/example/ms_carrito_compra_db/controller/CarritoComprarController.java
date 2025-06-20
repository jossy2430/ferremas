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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ms_carrito_compra_db.model.CarritoCompra;
import com.example.ms_carrito_compra_db.service.CarritoCompraService;

@RestController
@RequestMapping("api/carrito")
public class CarritoComprarController {
    private final CarritoCompraService carritoCompraService;

    public CarritoComprarController(CarritoCompraService carritoCompraService) {
        this.carritoCompraService = carritoCompraService;
    }

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
            cc.setIdCliente(carritoCompra.getIdCliente());
            cc.setFechaCreacion(carritoCompra.getFechaCreacion());
            cc.setActivo(carritoCompra.getActivo());
            cc.setTotal(carritoCompra.getTotal());

            cc.getItems().clear();
            if (carritoCompra.getItems() != null) {
                cc.getItems().addAll(carritoCompra.getItems());
            }

            carritoCompraService.save(cc);
            return ResponseEntity.ok(cc);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    @DeleteMapping("/{idCarrito}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer idCarrito){
        try {
            carritoCompraService.delete(idCarrito);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Agregar producto al carrito
    @PostMapping("/{idCarrito}/agregar-productos")
    public ResponseEntity<CarritoCompra> agregarProducto(
            @PathVariable Integer idCarrito,
            @RequestParam Integer idProducto,
            @RequestParam Integer cantidad,
            @RequestParam Double precioUnitario,
            @RequestParam(required = false) Double descuento) {
        try {
            CarritoCompra carrito = carritoCompraService.agregarProductos(idCarrito, idProducto, cantidad, precioUnitario, descuento);
            return ResponseEntity.ok(carrito);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar producto del carrito
    @DeleteMapping("/{idCarrito}/eliminar-producto/{idProducto}")
    public ResponseEntity<CarritoCompra> eliminarProducto(
            @PathVariable Integer idCarrito,
            @PathVariable Integer idProducto) {
        try {
            CarritoCompra carrito = carritoCompraService.eliminarProducto(idCarrito, idProducto);
            return ResponseEntity.ok(carrito);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Vaciar carrito
    @PostMapping("/{idCarrito}/vaciar")
    public ResponseEntity<CarritoCompra> vaciarCarrito(@PathVariable Integer idCarrito) {
        try {
            CarritoCompra carrito = carritoCompraService.vaciarCarrito(idCarrito);
            return ResponseEntity.ok(carrito);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<CarritoCompra> buscarPorCliente(@PathVariable Integer idCliente){
        try {
            CarritoCompra carritoCompra = carritoCompraService.findCarritoActivoByCliente(idCliente);
            if (carritoCompra != null) {
                return ResponseEntity.ok(carritoCompra);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
