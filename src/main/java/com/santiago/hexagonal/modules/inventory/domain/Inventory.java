package com.santiago.hexagonal.modules.inventory.domain;

import java.util.UUID;

public record Inventory(
                UUID id,
                UUID productId,
                UUID warehouseId,
                int availableQuantity) {

}
