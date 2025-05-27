package com.example.ms_producto_categoria_bff.modelDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaDTO {
    private Integer idCategoria;
    private String nombreCategoria;

}
