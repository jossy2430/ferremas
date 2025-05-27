package com.example.ms_carrito_compra_db.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "carritoCompra")
public class CarritoCompra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCarrito;

    @Column(nullable = false)
    private Integer idCliente;

    @OneToMany(mappedBy = "carrito", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemCarrito> items;

    @Column(nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(nullable = false)
    private Boolean activo;

    @Column(nullable = false)
    private Double total;

}
