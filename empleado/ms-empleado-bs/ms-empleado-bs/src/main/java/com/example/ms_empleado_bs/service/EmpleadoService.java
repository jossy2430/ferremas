package com.example.ms_empleado_bs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.ms_empleado_bs.clients.EmpleadoBdFeignClient;
import com.example.ms_empleado_bs.modelDto.EmpleadoDTO;

@Service
public class EmpleadoService {
    @Autowired
    EmpleadoBdFeignClient empleadoBdFeignClient;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
        public List<EmpleadoDTO> selectAllEmpleado() {
        return empleadoBdFeignClient.selectAllEmpleado();
    }

    public EmpleadoDTO findById(Integer idEmpleado){
        return empleadoBdFeignClient.findById(idEmpleado);
    }

    public EmpleadoDTO save(EmpleadoDTO empleadoDTO) {
        return empleadoBdFeignClient.save(empleadoDTO);
    }

    public EmpleadoDTO actualizar(Integer idEmpleado, EmpleadoDTO empleadoDTO){
        return empleadoBdFeignClient.actualizar(idEmpleado, empleadoDTO);
    }

    public void eliminar(Integer idEmpleado){
        empleadoBdFeignClient.eliminar(idEmpleado);
    }

    public EmpleadoDTO findByUsuario(String usuario){
        return empleadoBdFeignClient.findByUsuario(usuario);
    }

    public EmpleadoDTO cambiarPassword(Integer id, String nuevoPassword) {
        return empleadoBdFeignClient.cambiarPassword(id, nuevoPassword);
    }

    public EmpleadoDTO validadEmpleado(String usuario, String password) {
    System.out.println("Intentando validar al usuario: " + usuario);
    EmpleadoDTO empleado = empleadoBdFeignClient.findByUsuario(usuario);
    if (empleado != null) {
        System.out.println("Usuario encontrado: " + empleado.getUsuario());
        System.out.println("Contraseña de la DB: " + empleado.getPassword());
        System.out.println("Contraseña ingresada: " + password);
        if (password.equals(empleado.getPassword())) {
            System.out.println("Las contraseñas coinciden.");
            return empleado;
        } else {
            System.out.println("Las contraseñas NO coinciden.");
        }
    } else {
        System.out.println("Usuario no encontrado: " + usuario);
    }
    return null;
}
}
