package com.santiago.hexagonal.modules.inventory.infrastructure.persistence;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.santiago.hexagonal.modules.inventory.infrastructure.entity.InventoryEntity;

public interface JpaInventoryRepository extends JpaRepository<InventoryEntity, UUID> {
    Optional<InventoryEntity> findByProductIdAndWarehouseId(UUID productId, UUID warehouseId);

    Optional<InventoryEntity> findByWarehouseId(UUID warehouseId);

    @EntityGraph(attributePaths = { "product" })
    Page<InventoryEntity> findByWarehouse_Id(UUID warehouseId, PageRequest pageRequest);

}
