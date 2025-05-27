package com.example.ms_pedido_bff.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ms_pedido_bff.clients.ItemPedidoBsFeignClient;
import com.example.ms_pedido_bff.modelDto.ItemPedidoDTO;

@Service
public class ItemPedidoService {
    @Autowired
    ItemPedidoBsFeignClient itemPedidoBsFeignClient;

    public List<ItemPedidoDTO> selectAllItemPedido() {
        return itemPedidoBsFeignClient.selectAllItemPedido();
    }

    public ItemPedidoDTO findById(Long idItemPedido){
        return itemPedidoBsFeignClient.findById(idItemPedido);
    }

    public ItemPedidoDTO save(ItemPedidoDTO itemPedidoDTO) {
        return itemPedidoBsFeignClient.save(itemPedidoDTO);
    }

    public ItemPedidoDTO actualizar(Long idItemPedido, ItemPedidoDTO itemPedidoDTO) {
        return itemPedidoBsFeignClient.actualizar(idItemPedido, itemPedidoDTO);
    }

    public void eliminar(Long idItemPedido) {
        itemPedidoBsFeignClient.eliminar(idItemPedido);
    }

}
