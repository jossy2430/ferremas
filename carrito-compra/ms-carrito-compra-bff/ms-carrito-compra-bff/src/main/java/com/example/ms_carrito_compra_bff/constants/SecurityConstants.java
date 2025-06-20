
package com.example.ms_carrito_compra_bff.constants;

public final class SecurityConstants {
    
    //ROLES
    public static final String ROLE_ADMIN = "ADMIN";
    public static final String ROLE_CLIENTE = "CLIENTE";
    public static final String ROLE_VENDEDOR = "VENDEDOR";
    public static final String ROLE_BODEGUERO = "BODEGUERO";
    
    //ENDPOINTS
    public static final String API_AUTH_PATTERN = "/api/auth/**";
    public static final String API_TEST_PATTERN = "/api/test/**";
    public static final String API_CARRITO_ADMIN_PATTERN = "/api/carrito/admin/**";
    public static final String API_CARRITO_MI_CARRITO = "/api/carrito/mi-carrito";
    public static final String API_CARRITO_AGREGAR_PATTERN = "/api/carrito/**/agregar-producto";
    public static final String API_CARRITO_ELIMINAR_PATTERN = "/api/carrito/**/eliminar-producto/**";
    public static final String API_CARRITO_VACIAR_PATTERN = "/api/carrito/**/vaciar";
    public static final String API_CARRITO_PATTERN = "/api/carrito/**";
    public static final String API_ITEMCARRITO_PATTERN = "/api/itemcarrito/**";
    
    //CREDENCIALES DE PRUEBA
    public static final String ADMIN_USERNAME = "admin";
    public static final String ADMIN_PASSWORD = "admin123";
    public static final String CLIENTE_USERNAME = "cliente";
    public static final String CLIENTE_PASSWORD = "cliente123";
    public static final String VENDEDOR_USERNAME = "vendedor";
    public static final String VENDEDOR_PASSWORD = "vendedor123";
    public static final String BODEGUERO_USERNAME = "bodeguero";
    public static final String BODEGUERO_PASSWORD = "bodeguero123";
    
    // Constructor privado para evitar instanciación
    private SecurityConstants() {
        throw new UnsupportedOperationException("Esta es una clase de constantes");
    }
}