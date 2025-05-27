package com.example.ms_carrito_compra_bs.modelDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemCarritoDTO {
    private Integer idItem;
    private CarritoCompraDTO carrito;
    private Integer idProducto;
    private Integer cantidad;
    private Double precioUnitario;
    private Double descuento;
    private Double subTotal;

}
