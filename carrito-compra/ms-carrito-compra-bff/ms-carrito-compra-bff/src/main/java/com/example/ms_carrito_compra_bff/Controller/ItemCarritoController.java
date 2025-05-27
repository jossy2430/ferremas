package com.example.ms_carrito_compra_bff.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ms_carrito_compra_bff.modelDto.ItemCarritoDTO;
import com.example.ms_carrito_compra_bff.service.ItemCarritoService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/itemcarrito")
public class ItemCarritoController {
    @Autowired
    ItemCarritoService itemCarritoService;

    @GetMapping
    public List<ItemCarritoDTO> selectAllitemCarrito(){
        return itemCarritoService.selectAllitemCarrito();
    }

    @GetMapping("/{idItem}")
    public ItemCarritoDTO findById(@PathVariable Integer idItem){
        return itemCarritoService.findById(idItem);
    }

    @PostMapping
    public ItemCarritoDTO save(@RequestBody ItemCarritoDTO itemCarritoDTO){
        return itemCarritoService.save(itemCarritoDTO);
    }

    @PutMapping("/{idItem}")
    public ItemCarritoDTO actualizar(@PathVariable Integer idItem, @RequestBody ItemCarritoDTO itemCarritoDTO){
        return itemCarritoService.actualizar(idItem, itemCarritoDTO);
    }

    @DeleteMapping("/{idItem}")
    public void delete(@PathVariable Integer idItem){
        itemCarritoService.delete(idItem);
    }

}
