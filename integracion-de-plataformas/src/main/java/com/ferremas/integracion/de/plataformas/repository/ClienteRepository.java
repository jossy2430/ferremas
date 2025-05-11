package com.ferremas.integracion.de.plataformas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ferremas.integracion.de.plataformas.model.Cliente;

import feign.Param;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    //usando jpql
    @Query("SELECT r FROM Cliente r WHERE r.rut = :rut")
    List<Cliente> buscarPorRut(@Param("rut") String rut);

    
}
