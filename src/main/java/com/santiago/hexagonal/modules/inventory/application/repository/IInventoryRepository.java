package com.santiago.hexagonal.modules.inventory.application.repository;

import java.util.Optional;
import java.util.UUID;

import com.santiago.hexagonal.domain.pagination.PageRequest;
import com.santiago.hexagonal.domain.pagination.PaginatedResult;
import com.santiago.hexagonal.modules.inventory.application.port.out.InventoryResponse;
import com.santiago.hexagonal.modules.inventory.domain.Inventory;

public interface IInventoryRepository {
    Optional<Inventory> findById(UUID id);

    Inventory save(Inventory inventory);

    Optional<Inventory> findByProductIdAndWarehouseId(UUID productId, UUID warehouseId);

    PaginatedResult<InventoryResponse> findInventoriesByWarehouse(UUID warehouseId, PageRequest pageRequest);

}
