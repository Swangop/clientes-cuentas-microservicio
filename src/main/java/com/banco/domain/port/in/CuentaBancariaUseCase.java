package com.banco.domain.port.in;

import com.banco.domain.model.CuentaBancaria;

import java.util.Optional;

/**
 * Puerto de entrada para operaciones de CuentaBancaria.
 * Define los casos de uso del dominio.
 */
public interface CuentaBancariaUseCase {

    /**
     * Crea una nueva cuenta bancaria.
     * Si el cliente no existe, se crea automáticamente.
     */
    CuentaBancaria crearCuenta(CuentaBancaria cuenta);

    /**
     * Actualiza el saldo de una cuenta bancaria.
     */
    Optional<CuentaBancaria> actualizarSaldo(Long idCuenta, Double nuevoTotal);

    /**
     * Obtiene una cuenta por su ID.
     */
    Optional<CuentaBancaria> obtenerCuentaPorId(Long id);
}
