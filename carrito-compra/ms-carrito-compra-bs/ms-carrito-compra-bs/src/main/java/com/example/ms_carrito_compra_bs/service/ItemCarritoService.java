package com.example.ms_carrito_compra_bs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ms_carrito_compra_bs.clients.ItemCarritoDbFeignClient;
import com.example.ms_carrito_compra_bs.modelDto.ItemCarritoDTO;

@Service
public class ItemCarritoService {
    @Autowired
    ItemCarritoDbFeignClient itemCarritoDbFeignClient;

    public List<ItemCarritoDTO> selectAllitemCarrito(){
        return itemCarritoDbFeignClient.selectAllitemCarrito();
    }

    public ItemCarritoDTO findById(Integer idItem){
        return itemCarritoDbFeignClient.findById(idItem);
    }

    public ItemCarritoDTO save(ItemCarritoDTO itemCarritoDTO){
        return itemCarritoDbFeignClient.save(itemCarritoDTO);
    }
    public ItemCarritoDTO actualizar(Integer idItem, ItemCarritoDTO itemCarritoDTO){
        return itemCarritoDbFeignClient.actualizar(idItem, itemCarritoDTO);
    }

    public void delete(Integer idItem){
        itemCarritoDbFeignClient.delete(idItem);
    }
}