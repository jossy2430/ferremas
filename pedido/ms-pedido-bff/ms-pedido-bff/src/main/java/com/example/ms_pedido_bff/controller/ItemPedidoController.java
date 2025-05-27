package com.example.ms_pedido_bff.controller;

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

import com.example.ms_pedido_bff.modelDto.ItemPedidoDTO;
import com.example.ms_pedido_bff.service.ItemPedidoService;

@RestController
@RequestMapping("api/item-pedido")
public class ItemPedidoController {
    @Autowired
    ItemPedidoService itemPedidoService;

    @GetMapping
    public List<ItemPedidoDTO> selectAllItemPedido() {
        return itemPedidoService.selectAllItemPedido();
    }

    @GetMapping("/{idItemPedido}")
    public ItemPedidoDTO findById(@PathVariable Long idItemPedido) {
        return itemPedidoService.findById(idItemPedido);
    }

    @PostMapping
    public ItemPedidoDTO save(@RequestBody ItemPedidoDTO itemPedidoDTO) {
        return itemPedidoService.save(itemPedidoDTO);
    }

    @PutMapping("/{idItemPedido}")
    public ItemPedidoDTO actualizar(@PathVariable Long idItemPedido, @RequestBody ItemPedidoDTO itemPedidoDTO) {
        return itemPedidoService.actualizar(idItemPedido, itemPedidoDTO);
    }

    @DeleteMapping("/{idItemPedido}")
    public void eliminar(@PathVariable Long idItemPedido) {
        itemPedidoService.eliminar(idItemPedido);
    }

}
