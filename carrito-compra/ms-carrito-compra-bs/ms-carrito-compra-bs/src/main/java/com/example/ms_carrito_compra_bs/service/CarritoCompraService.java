package com.example.ms_carrito_compra_bs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ms_carrito_compra_bs.clients.CarritoDbFeignClient;
import com.example.ms_carrito_compra_bs.modelDto.CarritoCompraDTO;

@Service
public class CarritoCompraService {
    @Autowired
    CarritoDbFeignClient carritoDbFeignClient;

    public List<CarritoCompraDTO> selectAllCarritoCompra() {
        return carritoDbFeignClient.selectAllCarritoCompra();
    }

    public CarritoCompraDTO findById(Integer idCarrito) {
        return carritoDbFeignClient.findById(idCarrito);
    }

    public CarritoCompraDTO save(CarritoCompraDTO carritoCompraDTO) {
        return carritoDbFeignClient.save(carritoCompraDTO);
    }

    public CarritoCompraDTO actualizar(Integer idCarrito, CarritoCompraDTO carritoCompraDTO) {
        return carritoDbFeignClient.actualizar(idCarrito, carritoCompraDTO);
    }

    public void delete(Integer idCarrito) {
        carritoDbFeignClient.delete(idCarrito);
    }

}
