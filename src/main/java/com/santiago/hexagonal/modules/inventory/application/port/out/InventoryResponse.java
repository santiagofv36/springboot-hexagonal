package com.santiago.hexagonal.modules.inventory.application.port.out;

import java.util.UUID;

import com.santiago.hexagonal.modules.product.domain.Product;

public record InventoryResponse(
        UUID id,
        Product product,
        int quantity) {
}
