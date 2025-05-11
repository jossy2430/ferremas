package com.ferremas.integracion.de.plataformas.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcliente")
    private Integer idcliente;

    @Column(name = "rut", nullable = false, length = 12)
    private String rut;

    @Column(name = "nombres", nullable = false, length = 100)
    private String nombres;

    @Column(name = "apellidos", length = 100)
    private String apellidos;

    @Column(name = "correo", nullable = false, unique = true, length = 100)
    private String correo;

    @Column(name = "password", length = 10)
    private String password;

    @Column(name = "fecharegistro")
    private LocalDateTime fecharegistro;

    @Column(name = "recibenotificacion")
    private Boolean recibenotificacion;

    @Column(name = "direccioncliente", length = 100)
    private String direccioncliente;

    @Column(name = "telefono", unique = true)
    private int telefono;

}
