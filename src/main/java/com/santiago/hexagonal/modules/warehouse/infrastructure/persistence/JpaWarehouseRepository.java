package com.santiago.hexagonal.modules.warehouse.infrastructure.persistence;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.santiago.hexagonal.modules.warehouse.infrastructure.entity.WarehouseEntity;

public interface JpaWarehouseRepository extends JpaRepository<WarehouseEntity, UUID> {
    Optional<WarehouseEntity> findByName(String name);

    Optional<WarehouseEntity> findById(UUID id);
}
