package com.santiago.hexagonal.modules.product.application.usecase;

import com.santiago.hexagonal.application.interfaces.IApplicationService;
import com.santiago.hexagonal.application.interfaces.UseCase;
import com.santiago.hexagonal.application.port.in.Authenticated;
import com.santiago.hexagonal.modules.product.application.port.in.CreateProductCommand;
import com.santiago.hexagonal.modules.product.application.repository.IProductRepository;
import com.santiago.hexagonal.modules.product.domain.Product;
import com.santiago.hexagonal.modules.product.domain.exception.ProductRegisteredException;
import com.santiago.hexagonal.modules.product.domain.exception.InvalidProductException;

@UseCase
public class CreateProductUseCase implements IApplicationService<CreateProductCommand, Product> {

    private final IProductRepository productRepository;

    public CreateProductUseCase(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Authenticated
    public Product execute(CreateProductCommand command) {
        if (command.name() == null || command.name().isBlank()) {
            throw new InvalidProductException("Product name is required");
        }

        if (command.sku() == null || command.sku().isBlank()) {
            throw new InvalidProductException("Product SKU is required");
        }

        if (productRepository.findBySku(command.sku()).isPresent()) {
            throw new ProductRegisteredException("Product with SKU " + command.sku() + " already registered");
        }

        Product product = new Product(null, command.name(), command.sku());
        return productRepository.save(product);
    }

}
