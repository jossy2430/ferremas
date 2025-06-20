package com.example.ms_carrito_compra_db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.ms_carrito_compra_db.model.CarritoCompra;

@Repository
public interface CarritoCompraRepository extends JpaRepository<CarritoCompra, Integer>{
    //usando JPQL
    @Query("SELECT c FROM CarritoCompra c WHERE c.idCarrito = :idCarrito")
    List<CarritoCompra> buscarPorId(@Param("idCarrito")Integer idCarrito);

    // NUEVO: Buscar carrito activo por cliente
    @Query("SELECT c FROM CarritoCompra c WHERE c.idCliente = :idCliente AND c.activo = true")
    CarritoCompra findByIdClienteAndActivoTrue(@Param("idCliente") Integer idCliente);
}