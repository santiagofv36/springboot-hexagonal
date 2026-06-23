package com.santiago.hexagonal.modules.inventory.infrastructure.persistence;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.santiago.hexagonal.domain.pagination.PageRequest;
import com.santiago.hexagonal.domain.pagination.PaginatedResult;
import com.santiago.hexagonal.infrastructure.exception.DatabaseException;
import com.santiago.hexagonal.modules.inventory.application.port.out.InventoryResponse;
import com.santiago.hexagonal.modules.inventory.application.repository.IInventoryRepository;
import com.santiago.hexagonal.modules.inventory.domain.Inventory;
import com.santiago.hexagonal.modules.inventory.infrastructure.entity.InventoryEntity;
import com.santiago.hexagonal.modules.inventory.infrastructure.mapper.InventoryMapper;
import com.santiago.hexagonal.modules.inventory.infrastructure.mapper.InventoryResponseMapper;

@Component
public class InventoryRepository implements IInventoryRepository {

    @Autowired
    private JpaInventoryRepository jpaInventoryRepository;

    @Autowired
    private InventoryMapper inventoryMapper;

    @Autowired
    private InventoryResponseMapper inventoryResponseMapper;

    @Override
    public Optional<Inventory> findById(UUID id) {
        try {
            return jpaInventoryRepository.findById(id)
                    .map(inventoryMapper::toDomain);
        } catch (Exception e) {
            throw new DatabaseException("Error finding inventory", e);
        }
    }

    @Override
    public Inventory save(Inventory inventory) {
        try {
            InventoryEntity entity = inventoryMapper.toEntity(inventory);
            return inventoryMapper.toDomain(jpaInventoryRepository.save(entity));
        } catch (Exception e) {
            throw new DatabaseException("Error saving inventory", e);
        }
    }

    @Override
    public Optional<Inventory> findByProductIdAndWarehouseId(UUID productId, UUID warehouseId) {
        try {
            return jpaInventoryRepository.findByProductIdAndWarehouseId(productId, warehouseId)
                    .map(inventoryMapper::toDomain);
        } catch (Exception e) {
            throw new DatabaseException("Error finding inventory", e);
        }
    }

    @Override
    public PaginatedResult<InventoryResponse> findInventoriesByWarehouse(UUID warehouseId, PageRequest pageRequest) {
        try {
            Page<InventoryEntity> page = jpaInventoryRepository.findByWarehouse_Id(warehouseId,
                    org.springframework.data.domain.PageRequest.of(pageRequest.page(), pageRequest.size()));

            List<InventoryResponse> items = page.getContent().stream().map(inventoryResponseMapper::toResponse)
                    .toList();
            return PaginatedResult.of(items, page.getNumber(), page.getSize(), (int) page.getTotalElements());
        } catch (Exception e) {
            throw new DatabaseException("Error finding inventory", e);
        }
    }

}
