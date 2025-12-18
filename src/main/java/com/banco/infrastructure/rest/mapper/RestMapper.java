package com.banco.infrastructure.rest.mapper;

import com.banco.domain.model.Cliente;
import com.banco.domain.model.CuentaBancaria;
import com.banco.infrastructure.rest.dto.ClienteDTO;
import com.banco.infrastructure.rest.dto.CuentaBancariaDTO;
import com.banco.infrastructure.rest.dto.CrearCuentaRequest;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Mapper para convertir entre entidades de dominio y DTOs REST.
 */
@Component
public class RestMapper {

    public ClienteDTO toDTO(Cliente cliente) {
        if (cliente == null)
            return null;
        return ClienteDTO.builder()
                .dni(cliente.getDni())
                .nombre(cliente.getNombre())
                .apellido1(cliente.getApellido1())
                .apellido2(cliente.getApellido2())
                .fechaNacimiento(cliente.getFechaNacimiento())
                .cuentas(Optional.ofNullable(cliente.getCuentas())
                        .orElse(Collections.emptyList())
                        .stream()
                        .map(this::toDTO)
                        .collect(Collectors.toList()))
                .build();
    }

    public CuentaBancariaDTO toDTO(CuentaBancaria cuenta) {
        if (cuenta == null)
            return null;
        return CuentaBancariaDTO.builder()
                .id(cuenta.getId())
                .dniCliente(cuenta.getDniCliente())
                .tipoCuenta(cuenta.getTipoCuenta())
                .total(cuenta.getTotal())
                .build();
    }

    public CuentaBancaria toDomain(CrearCuentaRequest request) {
        if (request == null)
            return null;
        return CuentaBancaria.builder()
                .dniCliente(request.getDniCliente())
                .tipoCuenta(request.getTipoCuenta())
                .total(request.getTotal())
                .build();
    }

    public List<ClienteDTO> toClienteDTOList(List<Cliente> clientes) {
        return clientes.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
