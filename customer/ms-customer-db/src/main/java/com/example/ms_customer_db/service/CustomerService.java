package com.example.ms_customer_db.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ms_customer_db.Repository.CustomerRepository;
import com.example.ms_customer_db.model.Customer;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> findAll(){
        return customerRepository.findAll();
    }

    public Customer findById(Integer idCliente){
        return customerRepository.findById(idCliente).get();
    }

    public Customer save(Customer customer){
        if (customer.getIdCliente() == null) {
            customer.setFechaResgistro(LocalDateTime.now());
        }
        return customerRepository.save(customer);
    }

    public void delete(Integer idCliente){
        customerRepository.deleteById(idCliente);
    }

    public Optional<Customer> findByCorreo(String correo){
        return customerRepository.findByCorreo(correo);
    }

}
