package com.example.ms_pedido_bff.modelDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemPedidoDTO {
    private Long idItemPedido;
    private Integer idProducto;
    private Integer cantidad;
    private Double precioUnitario;
    private Double descuento;
    private Double subTotal;

}
