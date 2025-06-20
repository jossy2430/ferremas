package com.example.ms_empleado_bs.modelDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmpleadoDTO {
    private Integer idEmpleado;
    private String nombres;
    private String apellidos;
    private String rut;
    private String usuario;
    private String password;
    private String rol;
    private Boolean primerLogin;

}
