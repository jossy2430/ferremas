package com.example.ms_empleado_db.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ms_empleado_db.model.Empleado;
import com.example.ms_empleado_db.repository.EmpleadoRepository;

@Service
@Transactional
public class EmpleadoService {
    @Autowired
    private EmpleadoRepository empleadoRepository;

    public List<Empleado> findAll(){
        return empleadoRepository.findAll();
    }

    public Empleado findById(Integer idEmpleado){
        return empleadoRepository.findById(idEmpleado).get();
    }

    public Empleado save(Empleado empleado){
        // Guarda la contraseña directamente (sin encriptar)
        return empleadoRepository.save(empleado);
    }

    public void eliminar(Integer idEmpleado){
        empleadoRepository.deleteById(idEmpleado);
    }

    public Optional<Empleado> findByUsuario(String usuario){
        return empleadoRepository.findByUsuario(usuario);
    }

    //método cambio de contraseña
    public Empleado cambiarPassword(Integer idEmpleado, String nuevoPassword){
        Empleado empleado = findById(idEmpleado);
        // Guarda la nueva contraseña directamente (sin encriptar)
        empleado.setPassword(nuevoPassword);
        empleado.setPrimerLogin(false);
        return empleadoRepository.save(empleado);
    }

}