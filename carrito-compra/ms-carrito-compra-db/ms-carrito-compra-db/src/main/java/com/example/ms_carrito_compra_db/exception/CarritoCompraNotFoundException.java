package com.example.ms_carrito_compra_db.exception;

public class CarritoCompraNotFoundException extends RuntimeException {
    
    public CarritoCompraNotFoundException(String message) {
        super(message);
    }
    
    public CarritoCompraNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}