package com.santiago.hexagonal.modules.inventory.application.usecase;

import java.util.UUID;

import com.santiago.hexagonal.application.interfaces.IApplicationService;
import com.santiago.hexagonal.application.interfaces.UseCase;
import com.santiago.hexagonal.modules.inventory.application.port.in.CreateInventoryCommand;
import com.santiago.hexagonal.modules.inventory.application.repository.IInventoryRepository;
import com.santiago.hexagonal.modules.inventory.domain.Inventory;
import com.santiago.hexagonal.modules.inventory.domain.exception.InvalidInventoryException;
import com.santiago.hexagonal.modules.inventory.domain.exception.InventoryExistsException;
import com.santiago.hexagonal.modules.product.application.repository.IProductRepository;
import com.santiago.hexagonal.modules.warehouse.application.repository.IWarehouseRepository;

@UseCase
public class CreateInventory implements IApplicationService<CreateInventoryCommand, Inventory> {

    private final IInventoryRepository inventoryRepository;
    private final IProductRepository productRepository;
    private final IWarehouseRepository warehouseRepository;

    public CreateInventory(IInventoryRepository inventoryRepository, IProductRepository productRepository,
            IWarehouseRepository warehouseRepository) {
        this.inventoryRepository = inventoryRepository;
        this.productRepository = productRepository;
        this.warehouseRepository = warehouseRepository;
    }

    @Override
    public Inventory execute(CreateInventoryCommand command) {

        validate(command);

        Inventory inventory = new Inventory(null, command.productId(), command.warehouseId(),
                command.availableQuantity());
        return inventoryRepository.save(inventory);

    }

    private void validateInput(CreateInventoryCommand command) {
        if (command.productId() == null) {
            throw new InvalidInventoryException("Product ID is required");
        }

        if (command.warehouseId() == null) {
            throw new InvalidInventoryException("Warehouse ID is required");
        }

        if (command.availableQuantity() < 0) {
            throw new InvalidInventoryException("Available quantity must be non-negative");
        }
    }

    private void validateProduct(UUID productId) {
        if (productRepository.findById(productId).isEmpty()) {
            throw new InvalidInventoryException("Product not found");
        }
    }

    private void validateWarehouse(UUID warehouseId) {
        if (warehouseRepository.findById(warehouseId).isEmpty()) {
            throw new InvalidInventoryException("Warehouse not found");
        }
    }

    private void validateInventoryExists(UUID productId, UUID warehouseId) {
        if (inventoryRepository.findByProductIdAndWarehouseId(productId, warehouseId).isPresent()) {
            throw new InventoryExistsException("Inventory already exists");
        }
    }

    private void validate(CreateInventoryCommand command) {
        validateInput(command);
        validateProduct(command.productId());
        validateWarehouse(command.warehouseId());
        validateInventoryExists(command.productId(), command.warehouseId());
    }
}
