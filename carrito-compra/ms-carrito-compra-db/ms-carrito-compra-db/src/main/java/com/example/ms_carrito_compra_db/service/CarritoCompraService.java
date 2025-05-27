package com.example.ms_carrito_compra_db.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ms_carrito_compra_db.model.CarritoCompra;
import com.example.ms_carrito_compra_db.repository.CarritoCompraRepository;

@Service
@Transactional
public class CarritoCompraService {
    @Autowired
    private CarritoCompraRepository carritoCompraRepository;

    public List<CarritoCompra> findAll(){
        return carritoCompraRepository.findAll();
    }

    public CarritoCompra findById(Integer idCarrito){
        return carritoCompraRepository.findById(idCarrito).get();
    }

    public CarritoCompra save(CarritoCompra carritoCompra){
        if (carritoCompra.getIdCarrito() == null) {
            carritoCompra.setFechaCreacion(LocalDateTime.now());
        }
        return carritoCompraRepository.save(carritoCompra);
    }

    public void delete(Integer idCarrito){
        carritoCompraRepository.deleteById(idCarrito);
    }

}
