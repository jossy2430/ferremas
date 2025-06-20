package com.example.ms_carrito_compra_bff.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ms_carrito_compra_bff.modelDto.CarritoCompraDTO;
import com.example.ms_carrito_compra_bff.service.CarritoCompraService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/carrito")
public class CarritoCompraController {
    
    private final CarritoCompraService carritoCompraService;

    @Autowired
    public CarritoCompraController(CarritoCompraService carritoCompraService) {
        this.carritoCompraService = carritoCompraService;
    }

    @GetMapping("/admin")
    public ResponseEntity<?> getAll() {
        try {
            List<CarritoCompraDTO> carritos = carritoCompraService.selectAllCarritoCompra();
            return ResponseEntity.ok(carritos);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error al obtener carritos");
        }
    }

    @GetMapping("/{idCarrito}")
    public ResponseEntity<CarritoCompraDTO> getById(@PathVariable Integer idCarrito){
        try {
            CarritoCompraDTO carrito = carritoCompraService.findById(idCarrito);
            return ResponseEntity.ok(carrito);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(404).build();
        }
    }

    @PostMapping
    public ResponseEntity<CarritoCompraDTO> create(@RequestBody CarritoCompraDTO carritoCompraDTO){
        try {
            CarritoCompraDTO nuevoCarrito = carritoCompraService.save(carritoCompraDTO);
            return ResponseEntity.status(201).body(nuevoCarrito);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    @PutMapping("/{idCarrito}")
    public ResponseEntity<CarritoCompraDTO> update(@PathVariable Integer idCarrito, @RequestBody CarritoCompraDTO carritoCompraDTO){
        try {
            CarritoCompraDTO actulizado = carritoCompraService.actualizar(idCarrito, carritoCompraDTO);
            return ResponseEntity.ok(actulizado);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    @DeleteMapping("/{idCarrito}")
    public ResponseEntity<Void> delete(@PathVariable Integer idCarrito){
        try {
            carritoCompraService.delete(idCarrito);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<CarritoCompraDTO> getByCliente(@PathVariable Integer idCliente) {
        try {
            CarritoCompraDTO carrito = carritoCompraService.findByIdCliente(idCliente);
            return ResponseEntity.ok(carrito);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(404).build();
        }
    }

    @PostMapping("/{idCarrito}/agregar-productos")
    public ResponseEntity<CarritoCompraDTO> agregarProducto(
            @PathVariable Integer idCarrito,
            @RequestParam Integer idProducto,
            @RequestParam Integer cantidad,
            @RequestParam Double precioUnitario,
            @RequestParam(required = false) Double descuento) {
        try {
        CarritoCompraDTO carrito = carritoCompraService.agregarProducto(idCarrito, idProducto, cantidad, precioUnitario, descuento);
        return ResponseEntity.ok(carrito);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    @DeleteMapping("/{idCarrito}/eliminar-productos/{idProducto}")
    public ResponseEntity<CarritoCompraDTO> eliminarProducto(
            @PathVariable Integer idCarrito,
            @PathVariable Integer idProducto) {
        try {
            CarritoCompraDTO carrito = carritoCompraService.eliminarProducto(idCarrito, idProducto);
            return ResponseEntity.ok(carrito);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    @PostMapping("/{idCarrito}/vaciar")
    public ResponseEntity<CarritoCompraDTO> vaciarCarrito(@PathVariable Integer idCarrito) {
        try {
            CarritoCompraDTO carrito = carritoCompraService.vaciarCarrito(idCarrito);
            return ResponseEntity.ok(carrito);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }
}