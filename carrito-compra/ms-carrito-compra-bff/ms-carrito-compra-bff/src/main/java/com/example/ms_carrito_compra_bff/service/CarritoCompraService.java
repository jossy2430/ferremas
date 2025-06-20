package com.example.ms_carrito_compra_bff.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ms_carrito_compra_bff.clients.CarritoBsFeignClient;
import com.example.ms_carrito_compra_bff.modelDto.CarritoCompraDTO;

@Service
public class CarritoCompraService {

    private final CarritoBsFeignClient carritoBsFeignClient;

    @Autowired
    public CarritoCompraService(CarritoBsFeignClient carritoBsFeignClient) {
        this.carritoBsFeignClient = carritoBsFeignClient;
    }

    public List<CarritoCompraDTO> selectAllCarritoCompra() {
        return carritoBsFeignClient.selectAllCarritoCompra();
    }

    public CarritoCompraDTO findById(Integer idCarrito){
        return carritoBsFeignClient.findById(idCarrito);
    }

    public CarritoCompraDTO save(CarritoCompraDTO carritoCompraDTO){
        return carritoBsFeignClient.save(carritoCompraDTO);
    }

    public CarritoCompraDTO actualizar(Integer idCarrito, CarritoCompraDTO carritoCompraDTO){
        return carritoBsFeignClient.actualizar(idCarrito, carritoCompraDTO);
    }

    public void delete(Integer idCarrito){
        carritoBsFeignClient.delete(idCarrito);
    }

    public CarritoCompraDTO findByIdCliente(Integer idCliente){
        return carritoBsFeignClient.findByIdCliente(idCliente);
    }

    public CarritoCompraDTO agregarProducto(Integer idCarrito,Integer idProducto,Integer cantidad,Double precioUnitario,Double descuento) {
        return carritoBsFeignClient.agregarProductos(idCarrito, idProducto, cantidad, precioUnitario, descuento);
    }

    public CarritoCompraDTO eliminarProducto(Integer idCarrito, Integer idProducto){
        return carritoBsFeignClient.eliminarProducto(idCarrito, idProducto);
    }

    public CarritoCompraDTO vaciarCarrito(Integer idCarrito) {
        return carritoBsFeignClient.vaciarCarrito(idCarrito);
    }
}