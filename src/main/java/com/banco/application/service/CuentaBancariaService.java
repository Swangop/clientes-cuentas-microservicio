package com.banco.application.service;

import com.banco.domain.model.Cliente;
import com.banco.domain.model.CuentaBancaria;
import com.banco.domain.port.in.CuentaBancariaUseCase;
import com.banco.domain.port.out.ClienteRepositoryPort;
import com.banco.domain.port.out.CuentaBancariaRepositoryPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

/**
 * Servicio de aplicación que implementa los casos de uso de CuentaBancaria.
 */
@Service
public class CuentaBancariaService implements CuentaBancariaUseCase {

    private final CuentaBancariaRepositoryPort cuentaRepository;
    private final ClienteRepositoryPort clienteRepository;

    public CuentaBancariaService(CuentaBancariaRepositoryPort cuentaRepository,
            ClienteRepositoryPort clienteRepository) {
        this.cuentaRepository = cuentaRepository;
        this.clienteRepository = clienteRepository;
    }

    @Override
    @Transactional
    public CuentaBancaria crearCuenta(CuentaBancaria cuenta) {
        // Si el cliente no existe, lo creamos con datos mínimos
        if (!clienteRepository.existsByDni(cuenta.getDniCliente())) {
            Cliente nuevoCliente = Cliente.builder()
                    .dni(cuenta.getDniCliente())
                    .nombre("Nuevo")
                    .apellido1("Cliente")
                    .apellido2("")
                    .fechaNacimiento(LocalDate.of(2000, 1, 1))
                    .build();
            clienteRepository.save(nuevoCliente);
        }

        return cuentaRepository.save(cuenta);
    }

    @Override
    @Transactional
    public Optional<CuentaBancaria> actualizarSaldo(Long idCuenta, Double nuevoTotal) {
        return cuentaRepository.findById(idCuenta)
                .map(cuenta -> {
                    cuenta.setTotal(nuevoTotal);
                    return cuentaRepository.save(cuenta);
                });
    }

    @Override
    public Optional<CuentaBancaria> obtenerCuentaPorId(Long id) {
        return cuentaRepository.findById(id);
    }
}
