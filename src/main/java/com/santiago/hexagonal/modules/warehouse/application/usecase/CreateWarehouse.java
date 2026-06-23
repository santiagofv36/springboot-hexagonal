package com.santiago.hexagonal.modules.warehouse.application.usecase;

import com.santiago.hexagonal.application.interfaces.IApplicationService;
import com.santiago.hexagonal.application.interfaces.UseCase;
import com.santiago.hexagonal.application.port.in.Authenticated;
import com.santiago.hexagonal.modules.warehouse.application.port.in.CreateWarehouseCommand;
import com.santiago.hexagonal.modules.warehouse.application.repository.IWarehouseRepository;
import com.santiago.hexagonal.modules.warehouse.domain.Warehouse;
import com.santiago.hexagonal.modules.warehouse.domain.exception.WarehouseRegisteredException;
import com.santiago.hexagonal.modules.warehouse.domain.exception.InvalidWarehouseException;

@UseCase
public class CreateWarehouse implements IApplicationService<CreateWarehouseCommand, Warehouse> {

    private final IWarehouseRepository warehouseRepository;

    public CreateWarehouse(IWarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    @Override
    @Authenticated
    public Warehouse execute(CreateWarehouseCommand command) {
        if (command.name() == null || command.name().isBlank()) {
            throw new InvalidWarehouseException("Warehouse name is required");
        }

        if (command.location() == null || command.location().isBlank()) {
            throw new InvalidWarehouseException("Warehouse location is required");
        }

        if (warehouseRepository.findByName(command.name()).isPresent()) {
            throw new WarehouseRegisteredException("Warehouse with name " + command.name() + " already registered");
        }

        Warehouse warehouse = new Warehouse(null, command.name(), command.location());
        return warehouseRepository.save(warehouse);
    }

}
