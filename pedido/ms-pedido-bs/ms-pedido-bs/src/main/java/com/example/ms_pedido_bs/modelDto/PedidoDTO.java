package com.example.ms_pedido_bs.modelDto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDTO {
    private Long idPedido;
    private Integer idCliente;
    private List<ItemPedidoDTO> items;
    private LocalDateTime fechaPedido;
    private Double total;
    private String estado;
    private String direccionEnvio;
    private String medioPago;
    private String tipoEntrega;

}
