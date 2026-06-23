package com.santiago.hexagonal.modules.warehouse.application.usecase;

import com.santiago.hexagonal.application.exception.ResourceNotFoundException;
import com.santiago.hexagonal.application.interfaces.IApplicationService;
import com.santiago.hexagonal.application.interfaces.UseCase;
import com.santiago.hexagonal.application.port.in.Authenticated;
import com.santiago.hexagonal.modules.warehouse.application.port.in.GetWarehouseByIdCommand;
import com.santiago.hexagonal.modules.warehouse.application.repository.IWarehouseRepository;
import com.santiago.hexagonal.modules.warehouse.domain.Warehouse;

@UseCase
public class FindWarehouseById implements IApplicationService<GetWarehouseByIdCommand, Warehouse> {

    private final IWarehouseRepository warehouseRepository;

    public FindWarehouseById(IWarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    @Override
    @Authenticated
    public Warehouse execute(GetWarehouseByIdCommand command) {
        return warehouseRepository.findById(command.id())
                .orElseThrow(() -> new ResourceNotFoundException("Warehouse not found"));
    }

}
