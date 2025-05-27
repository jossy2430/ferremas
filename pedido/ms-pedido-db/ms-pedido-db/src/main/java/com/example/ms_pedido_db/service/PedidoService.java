package com.example.ms_pedido_db.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ms_pedido_db.model.Pedido;
import com.example.ms_pedido_db.repository.PedidoRepository;

@Service
@Transactional
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;

    public List<Pedido> findAll(){
        return pedidoRepository.findAll();
    }

    public Pedido findById(long idPedido){
        return pedidoRepository.findById(idPedido).get();
    }

    public Pedido save(Pedido pedido){
        if (pedido.getFechaPedido() == null) {
            pedido.setFechaPedido(LocalDateTime.now());
        }
        return pedidoRepository.save(pedido);
    }

    public void delete(Long idPedido){
        pedidoRepository.deleteById(idPedido);
    }

}
