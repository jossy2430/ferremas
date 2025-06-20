package com.example.ms_carrito_compra_db.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.example.ms_carrito_compra_db.model.CarritoCompra;
import com.example.ms_carrito_compra_db.model.ItemCarrito;
import com.example.ms_carrito_compra_db.repository.CarritoCompraRepository;
import com.example.ms_carrito_compra_db.exception.CarritoCompraNotFoundException;

@Service
@Transactional
public class CarritoCompraService {
    private final CarritoCompraRepository carritoCompraRepository;
    
    @Autowired
    public CarritoCompraService(CarritoCompraRepository carritoCompraRepository) {
        this.carritoCompraRepository = carritoCompraRepository;
    }

    public List<CarritoCompra> findAll(){
        return carritoCompraRepository.findAll();
    }

    public CarritoCompra findById(Integer idCarrito){
        Optional<CarritoCompra> optionalCarrito = carritoCompraRepository.findById(idCarrito);
        if (optionalCarrito.isPresent()) {
            return optionalCarrito.get();
        } else {
            throw new CarritoCompraNotFoundException("CarritoCompra not found with id: " + idCarrito);
        }
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

    //Agregar producto al carrito (la suma y la cantidad si ya existe)
    public CarritoCompra agregarProductos(Integer idCarrito, Integer idProducto, Integer cantidad, Double precioUnitario, Double descuento){
        CarritoCompra carrito = findById(idCarrito);
        Optional<ItemCarrito> itemOpt = carrito.getItems().stream().filter(i -> i.getIdProducto().equals(idProducto)).findFirst();

        if (itemOpt.isPresent()) {
            ItemCarrito item = itemOpt.get();
            item.setCantidad(item.getCantidad() + cantidad);
            item.setSubTotal((item.getPrecioUnitario()* item.getCantidad())- (item.getDescuento()!= null ? item.getDescuento(): 0));
        }else{
            ItemCarrito nuevo = new ItemCarrito();
            nuevo.setIdProducto(idProducto);
            nuevo.setCantidad(cantidad);
            nuevo.setPrecioUnitario(precioUnitario);
            nuevo.setDescuento(descuento != null ? descuento : 0.0); // ASEGURAR QUE NO SEA NULL
            nuevo.setSubTotal((precioUnitario * cantidad)- (descuento != null ? descuento : 0));
            nuevo.setCarrito(carrito);
            carrito.getItems().add(nuevo);
        }

        // llamada rest para descontar stock en el microservicio de producto
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = "http://localhost:8585/api/productos/" + idProducto + "/stock";
            restTemplate.put(url, cantidad * -1);
        } catch (Exception e) {
            // Log del error pero no detener la operación
            System.err.println("Error al actualizar stock: " + e.getMessage());
        }
        
        recalcularTotal(carrito);
        return carritoCompraRepository.save(carrito);
    }

    //eliminar producto del carrito
    public CarritoCompra eliminarProducto(Integer idCarrito, Integer idProducto){
        CarritoCompra carrito = findById(idCarrito);
        carrito.getItems().removeIf(item -> item.getIdProducto().equals(idProducto));
        recalcularTotal(carrito);
        return carritoCompraRepository.save(carrito);
    }

    //vaciar carrito
    public CarritoCompra vaciarCarrito(Integer idCarrito){
        CarritoCompra carrito = findById(idCarrito);
        carrito.getItems().clear();
        carrito.setTotal(0.0);
        return carritoCompraRepository.save(carrito);
    }

    public void recalcularTotal(CarritoCompra carrito){
        double total = carrito.getItems().stream().mapToDouble(ItemCarrito::getSubTotal).sum();
        carrito.setTotal(total);
    }

    public CarritoCompra findCarritoActivoByCliente(Integer idCliente){
        return carritoCompraRepository.findByIdClienteAndActivoTrue(idCliente);
    }
}