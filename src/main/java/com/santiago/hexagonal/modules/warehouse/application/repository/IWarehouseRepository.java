package com.santiago.hexagonal.modules.warehouse.application.repository;

import java.util.Optional;
import java.util.UUID;

import com.santiago.hexagonal.domain.pagination.PageRequest;
import com.santiago.hexagonal.domain.pagination.PaginatedResult;
import com.santiago.hexagonal.modules.warehouse.domain.Warehouse;

public interface IWarehouseRepository {
    Optional<Warehouse> findById(UUID id);

    Optional<Warehouse> findByName(String name);

    Warehouse save(Warehouse warehouse);

    PaginatedResult<Warehouse> findAll(PageRequest pageRequest);
}
