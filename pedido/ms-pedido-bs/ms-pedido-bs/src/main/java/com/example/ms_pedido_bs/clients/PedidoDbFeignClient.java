package com.example.ms_pedido_bs.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.ms_pedido_bs.modelDto.PedidoDTO;

@FeignClient(name = "ms-pedido-db", url = "http://localhost:8080/")
public interface PedidoDbFeignClient {

    @GetMapping("api/pedido")
    public List<PedidoDTO> selectAllPedido();

    @GetMapping("api/pedido/{idPedido}")
    public PedidoDTO findById(@PathVariable("idPedido") Long idPedido);

    @PostMapping("api/pedido")
    public PedidoDTO save(@RequestBody PedidoDTO pedidoDTO);

    @PutMapping("api/pedido/{idPedido}")
    public PedidoDTO actualizar(@PathVariable("idPedido") Long idPedido,@RequestBody PedidoDTO pedidoDTO);

    @DeleteMapping("api/pedido/{idPedido}")
    public void eliminar(@PathVariable("idPedido")Long idPedido);

}
