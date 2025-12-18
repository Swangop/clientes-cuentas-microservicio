package com.banco.infrastructure.persistence.adapter;

import com.banco.domain.model.Cliente;
import com.banco.domain.port.out.ClienteRepositoryPort;
import com.banco.infrastructure.persistence.entity.ClienteEntity;
import com.banco.infrastructure.persistence.mapper.ClienteMapper;
import com.banco.infrastructure.persistence.repository.ClienteJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Adaptador de persistencia para Cliente.
 * Implementa el puerto de salida usando Spring Data JPA.
 */
@Component
public class ClienteRepositoryAdapter implements ClienteRepositoryPort {

    private final ClienteJpaRepository jpaRepository;
    private final ClienteMapper mapper;

    public ClienteRepositoryAdapter(ClienteJpaRepository jpaRepository, ClienteMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public List<Cliente> findAll() {
        return jpaRepository.findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Cliente> findByDni(String dni) {
        return jpaRepository.findById(dni)
                .map(mapper::toDomain);
    }

    @Override
    public Cliente save(Cliente cliente) {
        ClienteEntity entity = mapper.toEntity(cliente);
        ClienteEntity saved = jpaRepository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public boolean existsByDni(String dni) {
        return jpaRepository.existsById(dni);
    }
}
