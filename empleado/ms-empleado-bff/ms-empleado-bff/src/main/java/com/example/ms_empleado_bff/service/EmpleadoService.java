package com.example.ms_empleado_bff.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ms_empleado_bff.clients.EmpleadoBsfeignClient;
import com.example.ms_empleado_bff.modelDto.EmpleadoDTO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmpleadoService {

    @Autowired
    private EmpleadoBsfeignClient empleadoBsfeignClient;

    public List<EmpleadoDTO> selectAllEmpleado() {
        try {
            log.info("Obteniendo lista de empleados del BS");
            List<EmpleadoDTO> empleados = empleadoBsfeignClient.selectAllEmpleado();
            log.info("Obtenidos {} empleados exitosamente", empleados.size());
            return empleados;
        } catch (Exception e) {
            log.error("Error al obtener empleados del BS: {}", e.getMessage());
            return Collections.emptyList();
        }
    }

    public EmpleadoDTO getEmpleadoById(Long id) {
        try {
            log.info("Obteniendo empleado con ID: {}", id);
            EmpleadoDTO empleado = empleadoBsfeignClient.getEmpleadoById(id);
            if (empleado != null) {
                log.info("Empleado encontrado: {}", empleado.getUsuario());
            } else {
                log.warn("Empleado con ID {} no encontrado", id);
            }
            return empleado;
        } catch (Exception e) {
            log.error("Error al obtener empleado con ID {}: {}", id, e.getMessage());
            return null;
        }
    }

    public EmpleadoDTO save(EmpleadoDTO empleadoDTO) {
        try {
            log.info("Creando nuevo empleado: {}", empleadoDTO.getUsuario());
            EmpleadoDTO nuevoEmpleado = empleadoBsfeignClient.save(empleadoDTO);
            log.info("Empleado creado exitosamente con ID: {}", nuevoEmpleado.getIdEmpleado());
            return nuevoEmpleado;
        } catch (Exception e) {
            log.error("Error al crear empleado: {}", e.getMessage());
            throw new RuntimeException("Error al crear empleado en el sistema", e); // ✅ Propagar error controlado
        }
    }

    public EmpleadoDTO update(EmpleadoDTO empleadoDTO) {
        try {
            log.info("Actualizando empleado ID: {} - Usuario: {}", 
                    empleadoDTO.getIdEmpleado(), empleadoDTO.getUsuario());
            

            if (empleadoDTO.getIdEmpleado() == null) {
                log.error("ID del empleado es null, no se puede actualizar");
                throw new IllegalArgumentException("ID del empleado es requerido para actualización");
            }
            
            Long id = empleadoDTO.getIdEmpleado().longValue();
            log.debug("Llamando al BS para actualizar empleado ID: {}", id);
            
            EmpleadoDTO resultado = empleadoBsfeignClient.update(id, empleadoDTO);
            
            if (resultado != null) {
                log.info("Empleado actualizado exitosamente: {}", resultado.getUsuario());
            } else {
                log.warn("BS retornó null para actualización del empleado ID: {}", id);
            }
            
            return resultado;
            
        } catch (IllegalArgumentException e) {
            log.error("Error de validación: {}", e.getMessage());
            throw e; // 
        } catch (Exception e) {
            log.error("Error inesperado al actualizar empleado ID {}: {}", 
                    empleadoDTO.getIdEmpleado(), e.getMessage());
            return null; // 
        }
    }

    public boolean delete(Long id) {
        try {
            log.info("Eliminando empleado con ID: {}", id);
            empleadoBsfeignClient.delete(id);
            log.info("Empleado eliminado exitosamente: {}", id);
            return true;
        } catch (Exception e) {
            log.error("Error al eliminar empleado ID {}: {}", id, e.getMessage());
            return false;
        }
    }

    public EmpleadoDTO findByUsuario(String usuario) {
        try {
            log.info("Buscando empleado por usuario: {}", usuario);
            EmpleadoDTO empleado = empleadoBsfeignClient.findByUsuario(usuario);
            if (empleado != null) {
                log.info("Empleado encontrado por usuario: {}", usuario);
            } else {
                log.warn("No se encontró empleado con usuario: {}", usuario);
            }
            return empleado;
        } catch (Exception e) {
            log.error("Error al buscar empleado por usuario {}: {}", usuario, e.getMessage());
            return null;
        }
    }


}