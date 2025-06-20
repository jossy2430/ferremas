package com.example.ms_empleado_bff.security;

import com.example.ms_empleado_bff.clients.EmpleadoBsfeignClient;
import com.example.ms_empleado_bff.modelDto.EmpleadoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceCustom implements UserDetailsService {

    @Autowired
    private EmpleadoBsfeignClient empleadoBsfeignClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        EmpleadoDTO empleado = empleadoBsfeignClient.findByUsuario(username);

        if (empleado == null) {
            throw new UsernameNotFoundException("Usuario no encontrado con el nombre: " + username);
        }

        return User.withUsername(empleado.getUsuario())
                   .password("") // No necesitamos la contraseña aquí, ya la validamos en el login
                .roles(empleado.getRol().replace("ROLE_", ""))
                .build();
    }
}