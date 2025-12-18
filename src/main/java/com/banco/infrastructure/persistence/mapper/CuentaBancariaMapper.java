package com.banco.infrastructure.persistence.mapper;

import com.banco.domain.model.CuentaBancaria;
import com.banco.infrastructure.persistence.entity.CuentaBancariaEntity;
import org.springframework.stereotype.Component;

/**
 * Mapper para convertir entre entidades de dominio y entidades JPA de
 * CuentaBancaria.
 */
@Component
public class CuentaBancariaMapper {

    public CuentaBancaria toDomain(CuentaBancariaEntity entity) {
        if (entity == null)
            return null;
        return CuentaBancaria.builder()
                .id(entity.getId())
                .dniCliente(entity.getDniCliente())
                .tipoCuenta(entity.getTipoCuenta())
                .total(entity.getTotal())
                .build();
    }

    public CuentaBancariaEntity toEntity(CuentaBancaria domain) {
        if (domain == null)
            return null;
        return CuentaBancariaEntity.builder()
                .id(domain.getId())
                .dniCliente(domain.getDniCliente())
                .tipoCuenta(domain.getTipoCuenta())
                .total(domain.getTotal())
                .build();
    }
}
