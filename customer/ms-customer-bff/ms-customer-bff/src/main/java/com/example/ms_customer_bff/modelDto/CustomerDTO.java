package com.example.ms_customer_bff.modelDTO;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
    private Integer idCliente;
    private String nombres;
    private String apellidos;
    private String rut;
    private String correo;
    private String password;
    private LocalDateTime fechaResgistro;
    private Boolean recibirNotificacion;
    private String direccion;
    private Integer telefono;

}

