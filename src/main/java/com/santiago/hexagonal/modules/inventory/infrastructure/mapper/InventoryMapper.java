package com.santiago.hexagonal.modules.inventory.infrastructure.mapper;

import java.util.UUID;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.santiago.hexagonal.infrastructure.IMapper;
import com.santiago.hexagonal.modules.inventory.domain.Inventory;
import com.santiago.hexagonal.modules.inventory.infrastructure.entity.InventoryEntity;
import com.santiago.hexagonal.modules.product.infrastructure.entity.ProductEntity;
import com.santiago.hexagonal.modules.warehouse.infrastructure.entity.WarehouseEntity;

@Mapper(componentModel = "spring")
public interface InventoryMapper extends IMapper<InventoryEntity, Inventory> {

    @Override
    @Mapping(target = "productId", source = "product.id")
    @Mapping(target = "warehouseId", source = "warehouse.id")
    Inventory toDomain(InventoryEntity entity);

    @Override
    @Mapping(target = "product", source = "productId", qualifiedByName = "mapToProduct")
    @Mapping(target = "warehouse", source = "warehouseId", qualifiedByName = "mapToWarehouse")
    InventoryEntity toEntity(Inventory domain);

    // 🔧 Custom mapping methods (disambiguated)

    @Named("mapToProduct")
    default ProductEntity mapToProduct(UUID id) {
        if (id == null)
            return null;
        return new ProductEntity(id);
    }

    @Named("mapToWarehouse")
    default WarehouseEntity mapToWarehouse(UUID id) {
        if (id == null)
            return null;
        return new WarehouseEntity(id);
    }
}