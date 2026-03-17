package com.santiago.hexagonal.modules.product.infrastructure;

import java.net.URI;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.santiago.hexagonal.application.interfaces.IApplicationService;
import com.santiago.hexagonal.domain.pagination.PageRequest;
import com.santiago.hexagonal.domain.pagination.PaginatedResult;
import com.santiago.hexagonal.modules.product.application.port.in.CreateProductCommand;
import com.santiago.hexagonal.modules.product.application.port.in.GetProductByIdCommand;
import com.santiago.hexagonal.modules.product.domain.Product;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private IApplicationService<CreateProductCommand, Product> createProductUseCase;
    @Autowired
    private IApplicationService<GetProductByIdCommand, Product> getProductByIdUseCase;
    @Autowired
    private IApplicationService<PageRequest, PaginatedResult<Product>> getAllProductsUseCase;

    @PostMapping
    public ResponseEntity<Void> createProduct(@RequestBody CreateProductCommand command) {
        Product product = createProductUseCase.execute(command);
        return ResponseEntity.created(URI.create("/api/v1/products/" + product.id().toString())).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable UUID id) {
        Product product = getProductByIdUseCase.execute(new GetProductByIdCommand(id));
        return ResponseEntity.ok(product);
    }

    @GetMapping
    public ResponseEntity<PaginatedResult<Product>> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int perPage) {
        PaginatedResult<Product> products = getAllProductsUseCase.execute(new PageRequest(page, perPage));
        return ResponseEntity.ok(products);
    }

}
