package com.banco.infrastructure.persistence.mapper;

import com.banco.domain.model.Cliente;
import com.banco.infrastructure.persistence.entity.ClienteEntity;
import org.springframework.stereotype.Component;

/**
 * Mapper para convertir entre entidades de dominio y entidades JPA de Cliente.
 */
@Component
public class ClienteMapper {

    public Cliente toDomain(ClienteEntity entity) {
        if (entity == null)
            return null;
        return Cliente.builder()
                .dni(entity.getDni())
                .nombre(entity.getNombre())
                .apellido1(entity.getApellido1())
                .apellido2(entity.getApellido2())
                .fechaNacimiento(entity.getFechaNacimiento())
                .build();
    }

    public ClienteEntity toEntity(Cliente domain) {
        if (domain == null)
            return null;
        return ClienteEntity.builder()
                .dni(domain.getDni())
                .nombre(domain.getNombre())
                .apellido1(domain.getApellido1())
                .apellido2(domain.getApellido2())
                .fechaNacimiento(domain.getFechaNacimiento())
                .build();
    }
}
