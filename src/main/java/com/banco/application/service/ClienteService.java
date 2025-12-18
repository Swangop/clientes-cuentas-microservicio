package com.banco.application.service;

import com.banco.domain.model.Cliente;
import com.banco.domain.model.CuentaBancaria;
import com.banco.domain.port.in.ClienteUseCase;
import com.banco.domain.port.out.ClienteRepositoryPort;
import com.banco.domain.port.out.CuentaBancariaRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Servicio de aplicación que implementa los casos de uso de Cliente.
 */
@Service
public class ClienteService implements ClienteUseCase {

    private final ClienteRepositoryPort clienteRepository;
    private final CuentaBancariaRepositoryPort cuentaRepository;

    public ClienteService(ClienteRepositoryPort clienteRepository,
            CuentaBancariaRepositoryPort cuentaRepository) {
        this.clienteRepository = clienteRepository;
        this.cuentaRepository = cuentaRepository;
    }

    @Override
    public List<Cliente> obtenerTodosLosClientes() {
        return clienteRepository.findAll().stream()
                .map(this::cargarCuentasDelCliente)
                .collect(Collectors.toList());
    }

    @Override
    public List<Cliente> obtenerClientesMayoresDeEdad() {
        return clienteRepository.findAll().stream()
                .map(this::cargarCuentasDelCliente)
                .filter(Cliente::esMayorDeEdad)
                .collect(Collectors.toList());
    }

    @Override
    public List<Cliente> obtenerClientesConCuentaSuperiorA(Double cantidad) {
        return clienteRepository.findAll().stream()
                .map(this::cargarCuentasDelCliente)
                .filter(cliente -> cliente.getTotalCuentas() > cantidad)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Cliente> obtenerClientePorDni(String dni) {
        return clienteRepository.findByDni(dni)
                .map(this::cargarCuentasDelCliente);
    }

    @Override
    public Cliente guardarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    /**
     * Carga las cuentas bancarias asociadas a un cliente.
     */
    private Cliente cargarCuentasDelCliente(Cliente cliente) {
        List<CuentaBancaria> cuentas = cuentaRepository.findByDniCliente(cliente.getDni());
        cliente.setCuentas(cuentas);
        return cliente;
    }
}
