package com.example.ms_carrito_compra_db.model;

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
@Table(name = "itemCarrito")
public class ItemCarrito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idItem;

    @ManyToOne
    @JoinColumn(name = "idCarrito")
    private CarritoCompra carrito;

    @Column(nullable = false)
    private Integer idProducto;

    @Column(nullable = false)
    private Integer cantidad;

    @Column(nullable = false)
    private Double precioUnitario;

    @Column(nullable = false)
    private Double descuento;

    @Column(nullable = false)
    private Double subTotal;

}
