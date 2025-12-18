package com.banco.domain.port.in;

import com.banco.domain.model.Cliente;

import java.util.List;
import java.util.Optional;

/**
 * Puerto de entrada para operaciones de Cliente.
 * Define los casos de uso del dominio.
 */
public interface ClienteUseCase {

    /**
     * Obtiene todos los clientes con sus cuentas.
     */
    List<Cliente> obtenerTodosLosClientes();

    /**
     * Obtiene los clientes mayores de 18 años.
     */
    List<Cliente> obtenerClientesMayoresDeEdad();

    /**
     * Obtiene clientes cuya suma de cuentas supera la cantidad indicada.
     */
    List<Cliente> obtenerClientesConCuentaSuperiorA(Double cantidad);

    /**
     * Obtiene un cliente por su DNI.
     */
    Optional<Cliente> obtenerClientePorDni(String dni);

    /**
     * Guarda un nuevo cliente.
     */
    Cliente guardarCliente(Cliente cliente);
}
