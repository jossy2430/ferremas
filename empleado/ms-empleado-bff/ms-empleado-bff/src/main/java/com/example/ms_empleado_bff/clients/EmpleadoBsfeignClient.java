package com.example.ms_empleado_bff.clients;

import com.example.ms_empleado_bff.modelDto.EmpleadoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@FeignClient(name = "ms-empleado-bs", url = "http://localhost:9393")
public interface EmpleadoBsfeignClient {

    @PostMapping("/api/empleados/validar")
    Map<String, Object> validarUsuario(@RequestBody Map<String, String> credenciales);

    @GetMapping("/api/empleados")
    List<EmpleadoDTO> selectAllEmpleado();

    @GetMapping("/api/empleados/{id}")
    EmpleadoDTO getEmpleadoById(@PathVariable("id") Long id);

    @PostMapping("/api/empleados")
    EmpleadoDTO save(@RequestBody EmpleadoDTO empleadoDTO);

    @PutMapping("/api/empleados/{id}")
    EmpleadoDTO update(@PathVariable("id") Long id, @RequestBody EmpleadoDTO empleadoDTO);

    @DeleteMapping("/api/empleados/{id}")
    void delete(@PathVariable Long id);

    @GetMapping("/api/empleados/usuario/{usuario}")
    EmpleadoDTO findByUsuario(@PathVariable String usuario);
}