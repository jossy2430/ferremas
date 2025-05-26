package com.example.ms_customer_bs.modelDto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDTO {
    private Integer idCliente;
    private String nombres;
    private String apellidos;
    private String rut;
    private String correo;
    private String password;
    private LocalDateTime fechaResgistro;
    private Boolean recibirNotificaciones;
    private String direccion;
    private Integer telefono;
}
