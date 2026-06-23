package com.santiago.hexagonal.modules.product.infrastructure.mapper;

import org.mapstruct.Mapper;

import com.santiago.hexagonal.modules.product.domain.Product;
import com.santiago.hexagonal.modules.product.infrastructure.entity.ProductEntity;

import com.santiago.hexagonal.infrastructure.IMapper;

@Mapper(componentModel = "spring")
public interface ProductMapper extends IMapper<ProductEntity, Product> {

}
