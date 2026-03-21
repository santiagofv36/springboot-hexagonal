package com.santiago.hexagonal.modules.inventory.infrastructure.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.santiago.hexagonal.modules.inventory.application.port.out.InventoryResponse;
import com.santiago.hexagonal.modules.inventory.infrastructure.entity.InventoryEntity;

@Mapper(componentModel = "spring")
public interface InventoryResponseMapper {
    @Mapping(target = "product.id", source = "product.id")
    @Mapping(target = "product.name", source = "product.name")
    @Mapping(target = "product.sku", source = "product.sku")
    @Mapping(target = "quantity", source = "availableQuantity")
    InventoryResponse toResponse(InventoryEntity entity);
}
