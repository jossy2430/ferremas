package com.example.ms_pedido_bff.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ms_pedido_bff.clients.PedidoBsFeignClient;
import com.example.ms_pedido_bff.modelDto.PedidoDTO;

@Service
public class PedidoService {

    @Autowired
    PedidoBsFeignClient pedidoBsFeignClient;

    public List<PedidoDTO> selectAllPedido(){
        return pedidoBsFeignClient.selectAllPedido();
    }

    public PedidoDTO findById(Long idPedido){
        return pedidoBsFeignClient.findById(idPedido);
    }

    public PedidoDTO save(PedidoDTO pedidoDTO){
        return pedidoBsFeignClient.save(pedidoDTO);
    }

    public PedidoDTO actualizar(Long idPedido, PedidoDTO pedidoDTO){
        return pedidoBsFeignClient.actualizar(idPedido, pedidoDTO);
    }

    public void eliminar(Long idPedido){
        pedidoBsFeignClient.eliminar(idPedido);
    }


}
