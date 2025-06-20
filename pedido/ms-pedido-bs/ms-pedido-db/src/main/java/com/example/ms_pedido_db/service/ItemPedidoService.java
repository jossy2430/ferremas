package com.example.ms_pedido_db.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ms_pedido_db.model.ItemPedido;
import com.example.ms_pedido_db.repository.ItemPedidoRepository;

@Service
@Transactional
public class ItemPedidoService {
    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    public List<ItemPedido> findAll(){
        return itemPedidoRepository.findAll();
    }

    public ItemPedido findById(long idItemPedido){
        return itemPedidoRepository.findById(idItemPedido).get();
    }

    public ItemPedido save(ItemPedido itemPedido){
        return itemPedidoRepository.save(itemPedido);
    }

    public void delete(Long idItemPedido){
        itemPedidoRepository.deleteById(idItemPedido);
    }

}
