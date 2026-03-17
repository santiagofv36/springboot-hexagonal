package com.santiago.hexagonal.modules.warehouse.domain;

import java.util.UUID;

public record Warehouse(
        UUID id,
        String name,
        String location) {

}
