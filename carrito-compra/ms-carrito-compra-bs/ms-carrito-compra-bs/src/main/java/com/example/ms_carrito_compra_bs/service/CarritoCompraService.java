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

    //Agregar producto al carrito (la suma y la cantidad si ya existe)
    

    public CarritoCompraDTO agregarProducto(Integer idCarrito, Integer idProducto, Integer cantidad, Double precioUnitario, Double descuento) {
        CarritoCompraDTO carrito = carritoDbFeignClient.findById(idCarrito);

        // Buscar si el producto ya está en el carrito
        var itemOpt = carrito.getItems().stream().filter(i -> i.getIdProducto().equals(idProducto)).findFirst();

        if (itemOpt.isPresent()) {
            var item = itemOpt.get();
            item.setCantidad(item.getCantidad() + cantidad);
            item.setSubTotal((item.getPrecioUnitario() * item.getCantidad()) - (item.getDescuento() != null ? item.getDescuento() : 0));
        } else {
            var nuevo = new com.example.ms_carrito_compra_bs.modelDto.ItemCarritoDTO();
            nuevo.setIdProducto(idProducto);
            nuevo.setCantidad(cantidad);
            nuevo.setPrecioUnitario(precioUnitario);
            nuevo.setDescuento(descuento);
            nuevo.setSubTotal((precioUnitario * cantidad) - (descuento != null ? descuento : 0));
            carrito.getItems().add(nuevo);
        }

        recalcularTotal(carrito);
        return carritoDbFeignClient.save(carrito);
    }

    public CarritoCompraDTO eliminarProducto(Integer idCarrito, Integer idProducto) {
        CarritoCompraDTO carrito = carritoDbFeignClient.findById(idCarrito);
        carrito.getItems().removeIf(item -> item.getIdProducto().equals(idProducto));
        recalcularTotal(carrito);
        return carritoDbFeignClient.save(carrito);
    }

    public CarritoCompraDTO vaciarCarrito(Integer idCarrito) {
        CarritoCompraDTO carrito = carritoDbFeignClient.findById(idCarrito);
        carrito.getItems().clear();
        carrito.setTotal(0.0);
        return carritoDbFeignClient.save(carrito);
    }

    private void recalcularTotal(CarritoCompraDTO carrito) {
        double total = carrito.getItems().stream().mapToDouble(com.example.ms_carrito_compra_bs.modelDto.ItemCarritoDTO::getSubTotal).sum();
        carrito.setTotal(total);
    }


}
