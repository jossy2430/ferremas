package com.example.ms_empleado_db.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ms_empleado_db.model.Empleado;
import com.example.ms_empleado_db.service.EmpleadoService;

@RestController
@RequestMapping("api/empleados")
public class EmpleadoController {
    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping
    public ResponseEntity<List<Empleado>> listar(){
        List<Empleado> empleados = empleadoService.findAll();
        if (empleados.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(empleados, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Empleado> guardar(@RequestBody Empleado empleado){
        Empleado nuevoEmpleado = empleadoService.save(empleado);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoEmpleado);
    }

    @GetMapping("/{idEmpleado}")
    public ResponseEntity<Empleado> buscar(@PathVariable Integer idEmpleado){
        try {
            Empleado empleado = empleadoService.findById(idEmpleado);
            return ResponseEntity.ok(empleado);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{idEmpleado}")
    public ResponseEntity<Empleado> actualizar(@PathVariable Integer idEmpleado, @RequestBody Empleado empleado){
        try {
            Empleado em = empleadoService.findById(idEmpleado);
            em.setIdEmpleado(idEmpleado);
            em.setNombres(empleado.getNombres());
            em.setApellidos(empleado.getApellidos());
            em.setRut(empleado.getRut());
            em.setUsuario(empleado.getUsuario());
            em.setPassword(empleado.getPassword());
            em.setRol(empleado.getRol());
            em.setPrimerLogin(empleado.getPrimerLogin());

            empleadoService.save(em);
            return ResponseEntity.ok(empleado);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{idEmpleado}")
    public ResponseEntity<?> eliminar(@PathVariable Integer idEmpleado){
        try {
            empleadoService.eliminar(idEmpleado);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    //
    @GetMapping("/usuario/{usuario}")
    public ResponseEntity<Empleado> buscarPorUsuario(@PathVariable String usuario) {
        return empleadoService.findByUsuario(usuario)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/api/empleados/{id}/cambiar-password")
    public ResponseEntity<Empleado> cambiarPassword(@PathVariable Integer idEmpleado, @RequestBody String nuevoPassword){
        Empleado empleado = empleadoService.cambiarPassword(idEmpleado, nuevoPassword);
        return ResponseEntity.ok(empleado);

    }

    
}