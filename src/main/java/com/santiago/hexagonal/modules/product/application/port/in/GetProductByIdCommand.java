package com.santiago.hexagonal.modules.product.application.port.in;

import java.util.UUID;

public record GetProductByIdCommand(UUID id) {

}
