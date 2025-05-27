package com.example.ms_pedido_bff.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ms_pedido_bff.modelDto.PedidoDTO;
import com.example.ms_pedido_bff.service.PedidoService;

@RestController
@RequestMapping("api/pedido")
public class PedidoController {
    @Autowired
    PedidoService pedidoService;

    @GetMapping
    public List<PedidoDTO> selectAllPedido() {
        return pedidoService.selectAllPedido();
    }

    @GetMapping("/{idPedido}")
    public PedidoDTO findById(@PathVariable Long idPedido) {
        return pedidoService.findById(idPedido);
    }

    @PostMapping
    public PedidoDTO save(@RequestBody PedidoDTO pedidoDTO) {
        return pedidoService.save(pedidoDTO);
    }

    @PutMapping("/{idPedido}")
    public PedidoDTO actualizar(@PathVariable Long idPedido, @RequestBody PedidoDTO pedidoDTO) {
        return pedidoService.actualizar(idPedido, pedidoDTO);
    }

    @DeleteMapping("/{idPedido}")
    public void eliminar(@PathVariable Long idPedido) {
        pedidoService.eliminar(idPedido);
    }
}
