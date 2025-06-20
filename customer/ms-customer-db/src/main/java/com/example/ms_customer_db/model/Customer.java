package com.example.ms_customer_db.model;

import jakarta.persistence.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCliente;

    @Column(nullable = false, length = 25)
    private String nombres;

    @Column(nullable = false, length = 25)
    private String apellidos;

    @Column(nullable = false, length = 12)
    private String rut;

    @Column(nullable = false, unique = true, length = 100)
    private String correo;

    @Column(nullable = false, length = 255)
    private String password;

    @Column(nullable = false)
    private LocalDateTime fechaResgistro;

    @Column(nullable = false)
    private Boolean recibirNotificacion;

    @Column(nullable = false, length = 255)
    private String direccion;

    @Column(columnDefinition = "INT(9)")
    private Integer telefono;
}
