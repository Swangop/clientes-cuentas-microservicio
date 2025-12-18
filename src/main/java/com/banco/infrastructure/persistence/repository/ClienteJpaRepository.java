package com.banco.infrastructure.persistence.repository;

import com.banco.infrastructure.persistence.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio JPA para ClienteEntity.
 */
@Repository
public interface ClienteJpaRepository extends JpaRepository<ClienteEntity, String> {
}
