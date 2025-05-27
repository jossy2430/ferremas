package com.example.ms_pedido_bs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ms_pedido_bs.clients.ItemPedidoDbFeignClient;
import com.example.ms_pedido_bs.modelDto.ItemPedidoDTO;

@Service
public class ItemPedidoService {
    @Autowired
    ItemPedidoDbFeignClient itemPedidoDbFeignClient;

    public List<ItemPedidoDTO> selectAllItemPedido() {
        return itemPedidoDbFeignClient.selectAllItemPedido();
    }

    public ItemPedidoDTO findById(Long idItemPedido){
        return itemPedidoDbFeignClient.findById(idItemPedido);
    }

    public ItemPedidoDTO save(ItemPedidoDTO itemPedidoDTO) {
        return itemPedidoDbFeignClient.save(itemPedidoDTO);
    }

    public ItemPedidoDTO actualizar(Long idItemPedido, ItemPedidoDTO itemPedidoDTO) {
        return itemPedidoDbFeignClient.actualizar(idItemPedido, itemPedidoDTO);
    }

    public void eliminar(Long idItemPedido) {
        itemPedidoDbFeignClient.eliminar(idItemPedido);
    }

}
