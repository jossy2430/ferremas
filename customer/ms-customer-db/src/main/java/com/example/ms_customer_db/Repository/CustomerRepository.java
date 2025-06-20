package com.example.ms_customer_db.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.ms_customer_db.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{
    //USando JPQL
    @Query("SELECT c FROM Customer c WHERE c.rut = :rut")
    List<Customer> buscarPorRut(@Param("rut")String rut);

    Optional<Customer> findByCorreo(String correo);

}
