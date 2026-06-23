package com.santiago.hexagonal.modules.product.domain;

import java.util.UUID;

public record Product(UUID id, String name, String sku) {

}
