package com.example.ms_empleado_bff.modelDto;

import lombok.Data;


@Data
public class LoginRequest {
    private String usuario;
    private String password;

}
