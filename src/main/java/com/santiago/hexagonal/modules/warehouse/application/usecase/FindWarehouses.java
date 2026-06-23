package com.santiago.hexagonal.modules.warehouse.application.usecase;

import com.santiago.hexagonal.application.interfaces.IApplicationService;
import com.santiago.hexagonal.application.interfaces.UseCase;
import com.santiago.hexagonal.application.port.in.Authenticated;
import com.santiago.hexagonal.domain.pagination.PageRequest;
import com.santiago.hexagonal.domain.pagination.PaginatedResult;
import com.santiago.hexagonal.modules.warehouse.application.repository.IWarehouseRepository;
import com.santiago.hexagonal.modules.warehouse.domain.Warehouse;

@UseCase
public class FindWarehouses implements IApplicationService<PageRequest, PaginatedResult<Warehouse>> {

    private final IWarehouseRepository warehouseRepository;

    public FindWarehouses(IWarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    @Override
    @Authenticated
    public PaginatedResult<Warehouse> execute(PageRequest pageRequest) {
        return warehouseRepository.findAll(pageRequest);
    }

}
