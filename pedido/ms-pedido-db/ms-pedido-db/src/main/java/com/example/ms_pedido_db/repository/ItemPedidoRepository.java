package com.example.ms_pedido_db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.ms_pedido_db.model.ItemPedido;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {
    //JPQL
    @Query("SELECT i FROM ItemPedido i WHERE i.idItemPedido = :idItemPedido")
    List<ItemPedido> buscarPorId(@Param("idItemPedido")Long idItemPedido);
}
