package com.santiago.hexagonal.modules.product.application.usecase;

import com.santiago.hexagonal.application.interfaces.IApplicationService;
import com.santiago.hexagonal.application.interfaces.UseCase;
import com.santiago.hexagonal.application.port.in.Authenticated;
import com.santiago.hexagonal.domain.pagination.PageRequest;
import com.santiago.hexagonal.domain.pagination.PaginatedResult;
import com.santiago.hexagonal.modules.product.application.repository.IProductRepository;
import com.santiago.hexagonal.modules.product.domain.Product;

@UseCase
public class FindProducts implements IApplicationService<PageRequest, PaginatedResult<Product>> {

    private final IProductRepository productRepository;

    public FindProducts(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Authenticated
    public PaginatedResult<Product> execute(PageRequest pageRequest) {
        return productRepository.findAll(pageRequest);
    }

}
