package com.banco.domain.port.out;

import com.banco.domain.model.CuentaBancaria;

import java.util.List;
import java.util.Optional;

/**
 * Puerto de salida para persistencia de CuentaBancaria.
 * Interfaz que debe ser implementada por el adaptador de persistencia.
 */
public interface CuentaBancariaRepositoryPort {

    List<CuentaBancaria> findAll();

    List<CuentaBancaria> findByDniCliente(String dniCliente);

    Optional<CuentaBancaria> findById(Long id);

    CuentaBancaria save(CuentaBancaria cuenta);
}
