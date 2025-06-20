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

import com.example.ms_pedido_db.model.ItemPedido;
import com.example.ms_pedido_db.service.ItemPedidoService;

@RestController
@RequestMapping("api/item-pedido")
public class ItemPedidoController {
    @Autowired
    private ItemPedidoService itemPedidoService;

    @GetMapping
    public ResponseEntity<List<ItemPedido>> listar(){
        List<ItemPedido> itemPedidos = itemPedidoService.findAll();
        if (itemPedidos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(itemPedidos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ItemPedido> guardar(@RequestBody ItemPedido itemPedido){
        ItemPedido nuevoItemPedido = itemPedidoService.save(itemPedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoItemPedido);
    }

    @GetMapping("/{idItemPedido}")
    public ResponseEntity<ItemPedido> buscar(@PathVariable Long idItemPedido){
        try {
            ItemPedido itemPedido = itemPedidoService.findById(idItemPedido);
            return ResponseEntity.ok(itemPedido);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{idItemPedido}")
    public ResponseEntity<ItemPedido> actualizar(@PathVariable Long idItemPedido,@RequestBody ItemPedido itemPedido){
        try {
            ItemPedido ip = itemPedidoService.findById(idItemPedido);
            ip.setIdItemPedido(idItemPedido);
            ip.setPedido(itemPedido.getPedido());
            ip.setIdProducto(itemPedido.getIdProducto());
            ip.setCantidad(itemPedido.getCantidad());
            ip.setPrecioUnitario(itemPedido.getPrecioUnitario());
            ip.setDescuento(itemPedido.getDescuento());
            ip.setSubTotal(itemPedido.getSubTotal());

            itemPedidoService.save(ip);
            return ResponseEntity.ok(itemPedido);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{idItemPedido}")
    public ResponseEntity<?> eliminar(@PathVariable Long idItemPedido){
        try {
            itemPedidoService.delete(idItemPedido);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
