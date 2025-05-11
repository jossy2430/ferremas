package com.ferremas.integracion.de.plataformas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ferremas.integracion.de.plataformas.model.Cliente;
import com.ferremas.integracion.de.plataformas.repository.ClienteRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> findAll(){
        return clienteRepository.findAll();
    }
    
    public Cliente  findById(long idcliente){
        return clienteRepository.findById(idcliente).get();
    }

    public Cliente save(Cliente cliente){
        return clienteRepository.save(cliente);
    }

    public void delete(Long idcliente){
        clienteRepository.deleteById(idcliente);
    }

}
