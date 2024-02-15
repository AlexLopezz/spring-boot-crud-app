package com.alexdev.app.springbootdatajpa.services.impl;

import com.alexdev.app.springbootdatajpa.dao.IClientRepository;
import com.alexdev.app.springbootdatajpa.models.Client;
import com.alexdev.app.springbootdatajpa.services.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

// Servira para administrar los clientes de nuestro sistema, comunicandose con la capa de repositorio.
@Service // Marcamos esta clase para que el IoC de Spring lo inicialize...
public class ClientService implements IClientService {
    /*
    Patron service: Basado en el patron de diseño facade o fachada, ofrece un unico
    punto de acceso hacia distintos DAOs o REPOSITORIOS dentro de esta clase.
    Tambien se lo conoce como Capa de Logica de negocio
        * Esta capa de servicio suele ser intermediario entre la capa de Controlador y la de Repositorio
            * El controlador se comunica con esta capa y esta capa responde al controlador dependiendo de lo que responda el repositorio.
        * Esta capa puede conectarse a uno o mas DAOs y en base a eso realizar la logica necesaria para responder al Controlador.
     */

    @Autowired
    private IClientRepository repository;
    //private IClientDAO dao;

    @Override
    @Transactional(readOnly = true)
    public List<Client> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Client> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    @Transactional
    public void save(Client client) {
        repository.save(client);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Client> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    public void deleteAll(){
        repository.deleteAll();
    }
}