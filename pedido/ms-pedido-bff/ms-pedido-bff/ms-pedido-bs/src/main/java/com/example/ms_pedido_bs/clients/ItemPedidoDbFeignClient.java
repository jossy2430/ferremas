package com.example.ms_pedido_bs.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.ms_pedido_bs.modelDto.ItemPedidoDTO;

@FeignClient(name = "ms-itempedido-db", url = "http://localhost:8989/")
public interface ItemPedidoDbFeignClient {

    @GetMapping("api/item-pedido")
    public List<ItemPedidoDTO> selectAllItemPedido();

    @GetMapping("api/item-pedido/{idItemPedido}")
    public ItemPedidoDTO findById(@PathVariable("idItemPedido") Long idItemPedido);

    @PostMapping("api/item-pedido")
    public ItemPedidoDTO save(@RequestBody ItemPedidoDTO itemPedidoDTO);

    @PutMapping("api/item-pedido/{idItemPedido}")
    public ItemPedidoDTO actualizar(@PathVariable("idItemPedido") Long idItemPedido, @RequestBody ItemPedidoDTO itemPedidoDTO);

    @DeleteMapping("api/item-pedido/{idItemPedido}")
    public void eliminar(@PathVariable("idItemPedido")Long idItemPedido);

}
