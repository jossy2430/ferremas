package com.example.ms_pedido_db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.ms_pedido_db.model.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long>{
    //JPQL
    @Query("SELECT p FROM Pedido p WHERE p.idPedido = :idPedido")
    List<Pedido> buscarPorId(@Param("idPedido")Long idPedido);

}
