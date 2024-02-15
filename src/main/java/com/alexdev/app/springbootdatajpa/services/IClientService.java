package com.alexdev.app.springbootdatajpa.services;

import com.alexdev.app.springbootdatajpa.models.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
    Esta es una interfaz de negocio, que luego sera implementada mediante la clase
 concreta correspondiente.
 */
public interface IClientService {
    /**
     * Mostrara un listado de todos los clientes
     * @return Listado de clientes
     */
    List<Client> findAll();

    /**
     * Mostrara un listado paginado de todos los clientes
     * @param pageable Objeto encargado de paginar
     * @return Listado paginado de clientes.
     */
    Page<Client> findAll(Pageable pageable);

    /**
     * Guarda / Modifica un registro de cliente en base de datos.
     * @param client Objeto con los datos que deseemos almacenar en base de datos.
     */
    void save(Client client);

    /**
     * Obtiene un cliente(o no) de la base de datos.
     * @param id Unico del cliente.
     * @return Cliente encontrado(o no).
     */
    Optional<Client> findById(Long id);

    /**
     * Elimina un cliente de la base de datos.
     * @param id Unico del cliente.
     */
    void deleteById(Long id);

    /**
     * Elimina todos los clientes de la base de datos.
     */
    void deleteAll();
}