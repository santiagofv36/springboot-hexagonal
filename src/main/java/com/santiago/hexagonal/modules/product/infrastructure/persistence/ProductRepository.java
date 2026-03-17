package com.santiago.hexagonal.modules.product.infrastructure.persistence;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.santiago.hexagonal.domain.pagination.PageRequest;
import com.santiago.hexagonal.domain.pagination.PaginatedResult;
import com.santiago.hexagonal.infrastructure.exception.DatabaseException;
import com.santiago.hexagonal.modules.product.application.repository.IProductRepository;
import com.santiago.hexagonal.modules.product.domain.Product;
import com.santiago.hexagonal.modules.product.infrastructure.entity.ProductEntity;
import com.santiago.hexagonal.modules.product.infrastructure.mapper.ProductMapper;

@Component
public class ProductRepository implements IProductRepository {

    @Autowired
    private JpaProductRepository jpaProductRepository;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public Optional<Product> findBySku(String sku) {
        try {
            return jpaProductRepository.findBySku(sku).map(productMapper::toDomain);
        } catch (Exception e) {
            throw new DatabaseException("Error finding product");
        }
    }

    @Override
    public Optional<Product> findById(UUID id) {
        try {
            return jpaProductRepository.findById(id).map(productMapper::toDomain);
        } catch (Exception e) {
            throw new DatabaseException("Error finding product");
        }
    }

    @Override
    public Product save(Product product) {
        try {
            ProductEntity productEntity = productMapper.toEntity(product);
            return productMapper.toDomain(jpaProductRepository.save(productEntity));
        } catch (Exception e) {
            throw new DatabaseException("Error saving product", e);
        }
    }

    @Override
    public PaginatedResult<Product> findAll(PageRequest pageRequest) {
        int parsedPage = Math.max(0, pageRequest.page() - 1);
        int parsedSize = Math.max(1, pageRequest.size());
        try {
            Page<ProductEntity> page = jpaProductRepository.findAll(org.springframework.data.domain.PageRequest.of(
                    parsedPage,
                    parsedSize));
            List<Product> items = page.getContent().stream().map(productMapper::toDomain).toList();
            return PaginatedResult.of(items, page.getNumber(), page.getSize(), (int) page.getTotalElements());
        } catch (Exception e) {
            throw new DatabaseException("Error finding products");
        }
    }

}
