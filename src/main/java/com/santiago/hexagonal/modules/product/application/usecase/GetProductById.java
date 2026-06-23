package com.santiago.hexagonal.modules.product.application.usecase;

import com.santiago.hexagonal.application.exception.ResourceNotFoundException;
import com.santiago.hexagonal.application.interfaces.IApplicationService;
import com.santiago.hexagonal.application.interfaces.UseCase;
import com.santiago.hexagonal.application.port.in.Authenticated;
import com.santiago.hexagonal.modules.product.application.port.in.GetProductByIdCommand;
import com.santiago.hexagonal.modules.product.application.repository.IProductRepository;
import com.santiago.hexagonal.modules.product.domain.Product;

@UseCase
public class GetProductById implements IApplicationService<GetProductByIdCommand, Product> {

    private final IProductRepository productRepository;

    public GetProductById(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Authenticated
    public Product execute(GetProductByIdCommand command) {
        return productRepository.findById(command.id())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }

}
