package com.santiago.hexagonal.modules.inventory.application.port.in;

import java.util.UUID;

import com.santiago.hexagonal.domain.pagination.PageRequest;

public record GetInventoriesByWarehouseCommand(UUID id, PageRequest pageRequest) {

}
