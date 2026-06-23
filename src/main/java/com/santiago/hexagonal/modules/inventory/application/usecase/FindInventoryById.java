package com.santiago.hexagonal.modules.inventory.application.usecase;

import com.santiago.hexagonal.application.exception.ResourceNotFoundException;
import com.santiago.hexagonal.application.interfaces.IApplicationService;
import com.santiago.hexagonal.application.interfaces.UseCase;
import com.santiago.hexagonal.application.port.in.Authenticated;
import com.santiago.hexagonal.modules.inventory.application.port.in.GetInventoryByIdCommand;
import com.santiago.hexagonal.modules.inventory.application.repository.IInventoryRepository;
import com.santiago.hexagonal.modules.inventory.domain.Inventory;
import com.santiago.hexagonal.modules.inventory.domain.exception.InvalidInventoryException;

@UseCase
public class FindInventoryById implements IApplicationService<GetInventoryByIdCommand, Inventory> {

    private final IInventoryRepository inventoryRepository;

    public FindInventoryById(IInventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    @Authenticated
    public Inventory execute(GetInventoryByIdCommand command) {

        if (command.id() == null) {
            throw new InvalidInventoryException("Inventory ID is required");
        }

        return inventoryRepository.findById(command.id())
                .orElseThrow(() -> new ResourceNotFoundException("Inventory not found"));
    }

}
