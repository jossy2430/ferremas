package com.example.ms_carrito_compra_bs.modelDto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarritoCompraDTO {
    private Integer idCarrito;
    private Integer idCliente;
    private List<ItemCarritoDTO> items;
    private LocalDateTime fechaCreacion;
    private Boolean activo;
    private Double total;

}
