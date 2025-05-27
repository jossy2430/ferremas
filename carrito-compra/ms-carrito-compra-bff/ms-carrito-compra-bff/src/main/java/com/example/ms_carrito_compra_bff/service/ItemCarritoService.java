package com.example.ms_carrito_compra_bff.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ms_carrito_compra_bff.clients.ItemCarritoBsFeignClient;
import com.example.ms_carrito_compra_bff.modelDto.ItemCarritoDTO;

@Service
public class ItemCarritoService {
    @Autowired
    ItemCarritoBsFeignClient itemCarritoBsFeignClient;

    public List<ItemCarritoDTO> selectAllitemCarrito(){
        return itemCarritoBsFeignClient.selectAllitemCarrito();
    }

    public ItemCarritoDTO findById(Integer idItem){
        return itemCarritoBsFeignClient.findById(idItem);
    }

    public ItemCarritoDTO save(ItemCarritoDTO itemCarritoDTO){
        return itemCarritoBsFeignClient.save(itemCarritoDTO);
    }
    public ItemCarritoDTO actualizar(Integer idItem, ItemCarritoDTO itemCarritoDTO){
        return itemCarritoBsFeignClient.actualizar(idItem, itemCarritoDTO);
    }

    public void delete(Integer idItem){
        itemCarritoBsFeignClient.delete(idItem);
    }

}
