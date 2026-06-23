package com.santiago.hexagonal.modules.inventory.application.port.in;

import java.util.UUID;

public record CreateInventoryCommand(
        UUID productId,
        UUID warehouseId,
        int availableQuantity) {

}
