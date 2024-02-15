package com.alexdev.app.springbootdatajpa.dao;

import com.alexdev.app.springbootdatajpa.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
    Esta interfaz no hace falta que se implemente, ya que al extender de JpaRepository nosotros delegamos la
 responsabilidad a Spring, y es el propio framework el encargado de ofrecernos los metodos que realizan las operaciones
 tipicas (CRUD por ejemplo)... Solo tenemos que ocupar los metddos que nos brinda!
 */
@Repository // En esta ocacion, la interfaz de JPA en si ya es un componente que Spring reconoce, por lo cual es OPCIONAL indicar esta anotacion.
public interface IClientRepository extends JpaRepository<Client, Long> { } // Extendemos de JPARepository<T, ID> por que nos brinda mas funcionalidad (como la paginacion, crud y entre otras cosas)