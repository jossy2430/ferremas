package com.example.ms_empleado_db.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.ms_empleado_db.model.Empleado;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {
    //JPQL
    @Query("SELECT e FROM Empleado e WHERE e.idEmpleado = :idEmpleado")
    List<Empleado> buscarPorId(@Param("idEmpleado")Integer idEmpleado);

    //buscar por usuario login y admin
    Optional<Empleado> findByUsuario(String usuario);
}
