package com.banco.infrastructure.persistence.adapter;

import com.banco.domain.model.CuentaBancaria;
import com.banco.domain.port.out.CuentaBancariaRepositoryPort;
import com.banco.infrastructure.persistence.entity.CuentaBancariaEntity;
import com.banco.infrastructure.persistence.mapper.CuentaBancariaMapper;
import com.banco.infrastructure.persistence.repository.CuentaBancariaJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Adaptador de persistencia para CuentaBancaria.
 * Implementa el puerto de salida usando Spring Data JPA.
 */
@Component
public class CuentaBancariaRepositoryAdapter implements CuentaBancariaRepositoryPort {

    private final CuentaBancariaJpaRepository jpaRepository;
    private final CuentaBancariaMapper mapper;

    public CuentaBancariaRepositoryAdapter(CuentaBancariaJpaRepository jpaRepository,
            CuentaBancariaMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public List<CuentaBancaria> findAll() {
        return jpaRepository.findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<CuentaBancaria> findByDniCliente(String dniCliente) {
        return jpaRepository.findByDniCliente(dniCliente).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CuentaBancaria> findById(Long id) {
        return jpaRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public CuentaBancaria save(CuentaBancaria cuenta) {
        CuentaBancariaEntity entity = mapper.toEntity(cuenta);
        CuentaBancariaEntity saved = jpaRepository.save(entity);
        return mapper.toDomain(saved);
    }
}
