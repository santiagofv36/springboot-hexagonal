package com.santiago.hexagonal.modules.warehouse.infrastructure.persistence;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.santiago.hexagonal.domain.pagination.PageRequest;
import com.santiago.hexagonal.domain.pagination.PaginatedResult;
import com.santiago.hexagonal.infrastructure.exception.DatabaseException;
import com.santiago.hexagonal.modules.warehouse.application.repository.IWarehouseRepository;
import com.santiago.hexagonal.modules.warehouse.domain.Warehouse;
import com.santiago.hexagonal.modules.warehouse.infrastructure.entity.WarehouseEntity;
import com.santiago.hexagonal.modules.warehouse.infrastructure.mapper.WarehouseMapper;

@Component
public class WarehouseRepository implements IWarehouseRepository {

    @Autowired
    private JpaWarehouseRepository jpaWarehouseRepository;

    @Autowired
    private WarehouseMapper warehouseMapper;

    @Override
    public Optional<Warehouse> findByName(String name) {
        try {
            return jpaWarehouseRepository.findByName(name).map(warehouseMapper::toDomain);
        } catch (Exception e) {
            throw new DatabaseException("Error finding warehouse",e);
        }
    }

    @Override
    public Optional<Warehouse> findById(UUID id) {
        try {
            return jpaWarehouseRepository.findById(id).map(warehouseMapper::toDomain);
        } catch (Exception e) {
            throw new DatabaseException("Error finding warehouse",e);
        }
    }

    @Override
    public Warehouse save(Warehouse warehouse) {
        try {
            WarehouseEntity warehouseEntity = warehouseMapper.toEntity(warehouse);
            return warehouseMapper.toDomain(jpaWarehouseRepository.save(warehouseEntity));
        } catch (Exception e) {
            throw new DatabaseException("Error saving warehouse", e);
        }
    }

    @Override
    public PaginatedResult<Warehouse> findAll(PageRequest pageRequest) {
        int parsedPage = Math.max(0, pageRequest.page() - 1);
        int parsedSize = Math.max(1, pageRequest.size());
        try {
            Page<WarehouseEntity> page = jpaWarehouseRepository.findAll(org.springframework.data.domain.PageRequest.of(
                    parsedPage,
                    parsedSize));
            List<Warehouse> items = page.getContent().stream().map(warehouseMapper::toDomain).toList();
            return PaginatedResult.of(items, page.getNumber(), page.getSize(), (int) page.getTotalElements());
        } catch (Exception e) {
            throw new DatabaseException("Error finding warehouses",e);
        }
    }

}
