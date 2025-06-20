package com.example.ms_empleado_db.model;


import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "empleados")
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEmpleado;

    @Column( nullable = false, length = 50)
    private String nombres;

    @Column( nullable = false, length = 50)
    private String apellidos;

    @Column(nullable = false,length = 12)
    private String rut;

    @Column(nullable = false)
    private String usuario;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String rol; //admin, vendedor, bodeguero, contador

    @Column(nullable = false)
    private Boolean primerLogin;

    

}
