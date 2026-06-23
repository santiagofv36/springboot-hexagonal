package com.santiago.hexagonal.modules.warehouse.application.usecase;

import com.santiago.hexagonal.application.exception.ResourceNotFoundException;
import com.santiago.hexagonal.application.interfaces.IApplicationService;
import com.santiago.hexagonal.application.interfaces.UseCase;
import com.santiago.hexagonal.application.port.in.Authenticated;
import com.santiago.hexagonal.modules.warehouse.application.port.in.GetWarehouseByNameCommand;
import com.santiago.hexagonal.modules.warehouse.application.repository.IWarehouseRepository;
import com.santiago.hexagonal.modules.warehouse.domain.Warehouse;

@UseCase
public class FindWarehouseByName implements IApplicationService<GetWarehouseByNameCommand, Warehouse> {

    private final IWarehouseRepository warehouseRepository;

    public FindWarehouseByName(IWarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    @Override
    @Authenticated
    public Warehouse execute(GetWarehouseByNameCommand command) {
        return warehouseRepository.findByName(command.name())
                .orElseThrow(
                        () -> new ResourceNotFoundException("Warehouse with name " + command.name() + " not found"));
    }

}
