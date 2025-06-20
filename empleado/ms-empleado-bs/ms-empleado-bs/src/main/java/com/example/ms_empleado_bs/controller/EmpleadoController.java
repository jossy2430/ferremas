package com.example.ms_empleado_bs.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ms_empleado_bs.modelDto.AuthResponse;
import com.example.ms_empleado_bs.modelDto.EmpleadoDTO;
import com.example.ms_empleado_bs.service.EmpleadoService;

@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {
    @Autowired
    EmpleadoService empleadoService;


    @GetMapping
    public List<EmpleadoDTO> selectAllEmpleado(){
        return empleadoService.selectAllEmpleado();
    }

    @GetMapping("/{idEmpleado}")
    public EmpleadoDTO findById(@PathVariable Integer idEmpleado){
        return empleadoService.findById(idEmpleado);
    }

    @PostMapping
    public EmpleadoDTO save(@RequestBody EmpleadoDTO empleadoDTO){
        return empleadoService.save(empleadoDTO);
    }

    @PutMapping("/{idEmpleado}")
    public EmpleadoDTO actualizar(@PathVariable Integer idEmpleado, @RequestBody EmpleadoDTO empleadoDTO){
        return empleadoService.actualizar(idEmpleado, empleadoDTO);
    }

    @DeleteMapping("/{idEmpleado}")
    public void eliminar(@PathVariable Integer idEmpleado){
        empleadoService.eliminar(idEmpleado);
    }

    @GetMapping("/usuario/{usuario}")
    public EmpleadoDTO findByUsuario(@PathVariable String usuario) {
        return empleadoService.findByUsuario(usuario);
    }

    @PutMapping("/api/empleados/{id}/cambiar-password")
    public EmpleadoDTO cambiarPassword(@PathVariable Integer id, @RequestBody String nuevoPassword) {
        return empleadoService.cambiarPassword(id, nuevoPassword);
    }

    //validar credenciales y devolver un AuthResponse
    @PostMapping("/validar")
    public AuthResponse validarEmpleado(@RequestBody Map<String, String> body) {
        String usuario = body.get("usuario");
        String password = body.get("password");

        EmpleadoDTO empleado = empleadoService.validadEmpleado(usuario, password);

        if (empleado != null) {
            return new AuthResponse(true, empleado.getRol());
        } else {
        return new AuthResponse(false, null);
        }
    }
}
