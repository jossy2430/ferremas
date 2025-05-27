package com.example.ms_pedido_bs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ms_pedido_bs.clients.PedidoDbFeignClient;
import com.example.ms_pedido_bs.modelDto.PedidoDTO;

@Service
public class PedidoService {
    @Autowired
    PedidoDbFeignClient pedidoDbFeignClient;

    public List<PedidoDTO> selectAllPedido(){
        return pedidoDbFeignClient.selectAllPedido();
    }

    public PedidoDTO findById(Long idPedido){
        return pedidoDbFeignClient.findById(idPedido);
    }

    public PedidoDTO save(PedidoDTO pedidoDTO){
        return pedidoDbFeignClient.save(pedidoDTO);
    }

    public PedidoDTO actualizar(Long idPedido, PedidoDTO pedidoDTO){
        return pedidoDbFeignClient.actualizar(idPedido, pedidoDTO);
    }

    public void eliminar(Long idPedido){
        pedidoDbFeignClient.eliminar(idPedido);
    }

}
