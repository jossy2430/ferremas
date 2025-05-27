package com.example.ms_carrito_compra_bff.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ms_carrito_compra_bff.clients.CarritoBsFeignClient;
import com.example.ms_carrito_compra_bff.modelDto.CarritoCompraDTO;

@Service
public class CarritoCompraService {
    @Autowired
    CarritoBsFeignClient carritoBsFeignClient;

    public List<CarritoCompraDTO> selectAllCarritoCompra() {
        return carritoBsFeignClient.selectAllCarritoCompra();
    }

    public CarritoCompraDTO findById(Integer idCarrito) {
        return carritoBsFeignClient.findById(idCarrito);
    }

    public CarritoCompraDTO save(CarritoCompraDTO carritoCompraDTO) {
        return carritoBsFeignClient.save(carritoCompraDTO);
    }

    public CarritoCompraDTO actualizar(Integer idCarrito, CarritoCompraDTO carritoCompraDTO) {
        return carritoBsFeignClient.actualizar(idCarrito, carritoCompraDTO);
    }

    public void delete(Integer idCarrito) {
        carritoBsFeignClient.delete(idCarrito);
    }

}
