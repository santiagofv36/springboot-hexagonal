package com.santiago.hexagonal.modules.product.application.repository;

import java.util.Optional;
import java.util.UUID;

import com.santiago.hexagonal.domain.pagination.PageRequest;
import com.santiago.hexagonal.domain.pagination.PaginatedResult;
import com.santiago.hexagonal.modules.product.domain.Product;

public interface IProductRepository {
    Optional<Product> findBySku(String sku);

    Optional<Product> findById(UUID id);

    Product save(Product product);

    PaginatedResult<Product> findAll(PageRequest pageRequest);
}
