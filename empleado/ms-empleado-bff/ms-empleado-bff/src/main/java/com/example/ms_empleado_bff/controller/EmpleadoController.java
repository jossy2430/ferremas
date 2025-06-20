package com.example.ms_empleado_bff.controller;

import com.example.ms_empleado_bff.clients.EmpleadoBsfeignClient;
import com.example.ms_empleado_bff.modelDto.EmpleadoDTO;
import com.example.ms_empleado_bff.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMINISTRADOR', 'CONTADOR', 'VENDEDOR')")
    public ResponseEntity<Map<String, Object>> listarEmpleados() {
        try {
            List<EmpleadoDTO> empleados = empleadoService.selectAllEmpleado();
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("data", empleados);
            response.put("message", "Empleados obtenidos exitosamente");
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Error al obtener empleados");
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR', 'CONTADOR', 'VENDEDOR')")
    public ResponseEntity<Map<String, Object>> obtenerEmpleado(@PathVariable Long id) {
        try {
            EmpleadoDTO empleado = empleadoService.getEmpleadoById(id);
            
            if (empleado != null) {
                Map<String, Object> response = new HashMap<>();
                response.put("success", true);
                response.put("data", empleado);
                response.put("message", "Empleado encontrado");
                return ResponseEntity.ok(response);
            } else {
                Map<String, Object> response = new HashMap<>();
                response.put("success", false);
                response.put("message", "Empleado no encontrado");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Error al obtener empleado");
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMINISTRADOR', 'CONTADOR')")
    public ResponseEntity<Map<String, Object>> crearEmpleado(@RequestBody EmpleadoDTO empleadoDTO) {
        try {
            EmpleadoDTO nuevoEmpleado = empleadoService.save(empleadoDTO);
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("data", nuevoEmpleado);
            response.put("message", "Empleado creado exitosamente");
            
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Error al crear empleado");
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PutMapping("/{id}")
@PreAuthorize("hasRole('ADMINISTRADOR')")
public ResponseEntity<Map<String, Object>> actualizarEmpleado(@PathVariable Long id, @RequestBody EmpleadoDTO empleadoDTO) {
    try {
        System.out.println("=== ACTUALIZAR EMPLEADO ===");
        System.out.println("ID recibido: " + id);
        System.out.println("EmpleadoDTO recibido: " + empleadoDTO);
        
        // Convertir Long a Integer para el DTO
        empleadoDTO.setIdEmpleado(id.intValue());
        
        System.out.println("EmpleadoDTO con ID asignado: " + empleadoDTO);
        
        EmpleadoDTO empleadoActualizado = empleadoService.update(empleadoDTO);
        System.out.println("Respuesta del service: " + empleadoActualizado);
        
        if (empleadoActualizado != null) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("data", empleadoActualizado);
            response.put("message", "Empleado actualizado exitosamente");
            System.out.println("=== ACTUALIZACIÓN EXITOSA ===");
            return ResponseEntity.ok(response);
        } else {
            System.out.println("=== ACTUALIZACIÓN FALLIDA ===");
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "No se pudo actualizar el empleado - posiblemente no existe");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    } catch (Exception e) {
        System.out.println("=== ERROR EN ACTUALIZACIÓN ===");
        System.out.println("Error: " + e.getMessage());
        e.printStackTrace();
        
        Map<String, Object> response = new HashMap<>();
        response.put("success", false);
        response.put("message", "Error al actualizar empleado");
        response.put("error", e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<Map<String, Object>> eliminarEmpleado(@PathVariable Long id) {
        try {
            String requestId = java.util.UUID.randomUUID().toString().substring(0, 8);
            System.out.println("=== ELIMINAR EMPLEADO [" + requestId + "] ===");
            System.out.println("ID a eliminar: " + id);
    
            boolean eliminado = empleadoService.delete(id);
            System.out.println("Resultado eliminación [" + requestId + "]: " + eliminado);
    
            Map<String, Object> response = new HashMap<>();
            if (eliminado) {
                response.put("success", true);
                response.put("message", "Empleado eliminado exitosamente");
                response.put("requestId", requestId); // Para rastrear duplicados
                System.out.println("=== ELIMINACIÓN EXITOSA [" + requestId + "] ===");
                return ResponseEntity.ok(response);
            } else {
                response.put("success", false);
                response.put("message", "No se pudo eliminar el empleado");
                response.put("requestId", requestId);
                System.out.println("=== ELIMINACIÓN FALLIDA [" + requestId + "] ===");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
        } catch (Exception e) {
            String requestId = java.util.UUID.randomUUID().toString().substring(0, 8);
            System.out.println("=== ERROR EN ELIMINACIÓN [" + requestId + "] ===");
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
    
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Error al eliminar empleado");
            response.put("error", e.getMessage());
            response.put("requestId", requestId);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    
}