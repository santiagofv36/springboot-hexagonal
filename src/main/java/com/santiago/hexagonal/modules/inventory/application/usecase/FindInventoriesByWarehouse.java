package com.santiago.hexagonal.modules.inventory.application.usecase;

import com.santiago.hexagonal.application.interfaces.IApplicationService;
import com.santiago.hexagonal.application.interfaces.UseCase;
import com.santiago.hexagonal.domain.pagination.PaginatedResult;
import com.santiago.hexagonal.modules.inventory.application.port.in.GetInventoriesByWarehouseCommand;
import com.santiago.hexagonal.modules.inventory.application.port.out.InventoryResponse;
import com.santiago.hexagonal.modules.inventory.application.repository.IInventoryRepository;
import com.santiago.hexagonal.modules.inventory.domain.exception.InvalidInventoryException;

@UseCase
public class FindInventoriesByWarehouse
        implements IApplicationService<GetInventoriesByWarehouseCommand, PaginatedResult<InventoryResponse>> {

    private final IInventoryRepository inventoryRepository;

    public FindInventoriesByWarehouse(IInventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public PaginatedResult<InventoryResponse> execute(GetInventoriesByWarehouseCommand command) {

        if (command.id() == null) {
            throw new InvalidInventoryException("Warehouse ID is required");
        }

        return inventoryRepository.findInventoriesByWarehouse(command.id(), command.pageRequest());
    }

}
