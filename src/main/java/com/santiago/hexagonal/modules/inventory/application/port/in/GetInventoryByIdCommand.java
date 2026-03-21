package com.santiago.hexagonal.modules.inventory.application.port.in;

import java.util.UUID;

public record GetInventoryByIdCommand(UUID id) {

}
