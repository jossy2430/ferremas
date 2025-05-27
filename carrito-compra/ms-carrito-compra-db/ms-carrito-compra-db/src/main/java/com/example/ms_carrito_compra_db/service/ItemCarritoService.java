package com.example.ms_carrito_compra_db.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ms_carrito_compra_db.model.ItemCarrito;
import com.example.ms_carrito_compra_db.repository.ItemCarritoRepository;

@Service
@Transactional
public class ItemCarritoService {
    @Autowired

    private ItemCarritoRepository itemCarritoRepository;

    public List<ItemCarrito> findAll(){
        return itemCarritoRepository.findAll();
    }
    public ItemCarrito findById(Integer idItem){
        return itemCarritoRepository.findById(idItem).get();
    }

    public ItemCarrito save(ItemCarrito itemCarrito){
        return itemCarritoRepository.save(itemCarrito);
    }

    public void delete(Integer idItem){
        itemCarritoRepository.deleteById(idItem);
    }
}
