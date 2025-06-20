package com.example.ms_empleado_bs.clients;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.ms_empleado_bs.modelDto.AuthResponse;
import com.example.ms_empleado_bs.modelDto.EmpleadoDTO;

@FeignClient(name = "ms-empleado-db", url = "http://localhost:9292")
public interface EmpleadoBdFeignClient {

    @GetMapping("api/empleados")
    public List<EmpleadoDTO> selectAllEmpleado();

    @GetMapping("api/empleados/{idEmpleado}")
    public EmpleadoDTO findById(@PathVariable("idEmpleado")Integer idEmpleado);

    @PostMapping("api/empleados")
    public EmpleadoDTO save(@RequestBody EmpleadoDTO empleadoDTO);

    @PutMapping("api/empleados/{idEmpleado}")
    public EmpleadoDTO actualizar(@PathVariable("idEmpleado")Integer idEmpleado, @RequestBody EmpleadoDTO empleadoDTO);

    @DeleteMapping("api/empleados/{idEmpleado}")
    public void eliminar(@PathVariable("idEmpleado")Integer idEmpleado);

    @GetMapping("/api/empleados/usuario/{usuario}")
    EmpleadoDTO findByUsuario(@PathVariable("usuario") String usuario);

    @PutMapping("/api/empleados/{id}/cambiar-password")
    EmpleadoDTO cambiarPassword(@PathVariable("id") Integer id, @RequestBody String nuevoPassword);

    @PostMapping("/api/empleados/validar")
    AuthResponse validadUsuario(@RequestBody Map<String, String> authRequest);

    
}
