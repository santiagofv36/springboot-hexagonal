package com.santiago.hexagonal.modules.product.infrastructure.persistence;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.santiago.hexagonal.modules.product.infrastructure.entity.ProductEntity;

public interface JpaProductRepository extends JpaRepository<ProductEntity, UUID> {
    Optional<ProductEntity> findBySku(String sku);
}
