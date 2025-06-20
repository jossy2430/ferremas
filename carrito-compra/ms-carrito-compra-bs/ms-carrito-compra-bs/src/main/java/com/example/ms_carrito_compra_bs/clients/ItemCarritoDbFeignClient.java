package com.example.ms_carrito_compra_bs.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.ms_carrito_compra_bs.modelDto.ItemCarritoDTO;

@FeignClient(name = "ms-ItemCarrito-compra-db", url = "http://localhost:8686/")
public interface ItemCarritoDbFeignClient {

    @GetMapping("/api/itemcarrito")
    public List<ItemCarritoDTO> selectAllitemCarrito();

    @GetMapping("/api/itemcarrito/{idItem}")
    public ItemCarritoDTO findById(@PathVariable("idItem") Integer idItem);

    @PostMapping("/api/itemcarrito")
    public ItemCarritoDTO save(@RequestBody ItemCarritoDTO itemCarritoDTO);

    @PutMapping("/api/itemcarrito/{idItem}")
    public ItemCarritoDTO actualizar(@PathVariable("idItem") Integer idItem, @RequestBody ItemCarritoDTO itemCarritoDTO);

    @DeleteMapping("/api/itemcarrito/{idItem}")
    public void delete(@PathVariable("idItem") Integer idItem);

}
