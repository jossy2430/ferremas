package com.example.ms_carrito_compra_bff.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.ms_carrito_compra_bff.modelDto.ItemCarritoDTO;

@FeignClient(name = "ms-ItemCarrito-compra-bs", url = "http://localhost:8181/")
public interface ItemCarritoBsFeignClient {
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
