package com.example.ms_carrito_compra_db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.ms_carrito_compra_db.model.ItemCarrito;

@Repository
public interface ItemCarritoRepository extends JpaRepository<ItemCarrito, Integer>{
    ////USando JPQL
    @Query("SELECT i FROM ItemCarrito i WHERE i.idItem = :idItem")
    List<ItemCarrito> buscarPorIdItem(@Param("idItem")Integer idItem);
}
