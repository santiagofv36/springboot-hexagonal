package com.santiago.hexagonal.modules.warehouse.infrastructure.mapper;

import org.mapstruct.Mapper;

import com.santiago.hexagonal.infrastructure.IMapper;
import com.santiago.hexagonal.modules.warehouse.domain.Warehouse;
import com.santiago.hexagonal.modules.warehouse.infrastructure.entity.WarehouseEntity;

@Mapper(componentModel = "spring")
public interface WarehouseMapper extends IMapper<WarehouseEntity, Warehouse> {

}
