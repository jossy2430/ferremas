package com.example.ms_pedido_db.controller;

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

import com.example.ms_pedido_db.model.Pedido;
import com.example.ms_pedido_db.service.PedidoService;

@RestController
@RequestMapping("api/pedido")
public class PedidoController {
    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public ResponseEntity<List<Pedido>> listar(){
        List<Pedido> pedidos = pedidoService.findAll();
        if (pedidos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(pedidos, HttpStatus.OK);
    }

    @PostMapping
    public  ResponseEntity<Pedido> guardar(@RequestBody Pedido pedido){
        Pedido nuevoPedido = pedidoService.save(pedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPedido);
    }

    @GetMapping("/{idPedido}")
    public ResponseEntity<Pedido> buscar(@PathVariable Long idPedido){
        try {
            Pedido pedido = pedidoService.findById(idPedido);
            return ResponseEntity.ok(pedido);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{idPedido}")
    public ResponseEntity<Pedido> actualizar(@PathVariable Long idPedido, @RequestBody Pedido pedido){
        try {
            Pedido p = pedidoService.findById(idPedido);
            p.setIdPedido(idPedido);
            p.setFechaPedido(pedido.getFechaPedido());
            p.setTotal(pedido.getTotal());
            p.setEstado(pedido.getEstado());

            pedidoService.save(p);
            return ResponseEntity.ok(pedido);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{idPedido}")
    public ResponseEntity<?> eliminar(@PathVariable Long idPedido){
        try {
            pedidoService.delete(idPedido);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
