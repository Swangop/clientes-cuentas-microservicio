package com.banco.infrastructure.persistence.repository;

import com.banco.infrastructure.persistence.entity.CuentaBancariaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio JPA para CuentaBancariaEntity.
 */
@Repository
public interface CuentaBancariaJpaRepository extends JpaRepository<CuentaBancariaEntity, Long> {

    List<CuentaBancariaEntity> findByDniCliente(String dniCliente);
}
