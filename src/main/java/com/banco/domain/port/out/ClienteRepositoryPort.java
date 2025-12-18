package com.banco.domain.port.out;

import com.banco.domain.model.Cliente;

import java.util.List;
import java.util.Optional;

/**
 * Puerto de salida para persistencia de Cliente.
 * Interfaz que debe ser implementada por el adaptador de persistencia.
 */
public interface ClienteRepositoryPort {

    List<Cliente> findAll();

    Optional<Cliente> findByDni(String dni);

    Cliente save(Cliente cliente);

    boolean existsByDni(String dni);
}
