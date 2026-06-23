package com.santiago.hexagonal.modules.inventory.infrastructure;

import java.net.URI;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.santiago.hexagonal.application.interfaces.IApplicationService;
import com.santiago.hexagonal.domain.pagination.PageRequest;
import com.santiago.hexagonal.domain.pagination.PaginatedResult;
import com.santiago.hexagonal.modules.inventory.application.port.in.CreateInventoryCommand;
import com.santiago.hexagonal.modules.inventory.application.port.in.GetInventoryByIdCommand;
import com.santiago.hexagonal.modules.inventory.application.port.out.InventoryResponse;
import com.santiago.hexagonal.modules.inventory.application.port.in.GetInventoriesByWarehouseCommand;
import com.santiago.hexagonal.modules.inventory.domain.Inventory;

@RestController
@RequestMapping("/api/v1/inventory")
public class InventoryController {

    @Autowired
    private IApplicationService<GetInventoriesByWarehouseCommand, PaginatedResult<InventoryResponse>> paginateInventoriesByWarehouseUseCase;
    @Autowired
    private IApplicationService<CreateInventoryCommand, Inventory> createInventoryUseCase;
    @Autowired
    private IApplicationService<GetInventoryByIdCommand, Inventory> findInventoryByIdUseCase;

    @GetMapping("/warehouse/{warehouseId}")
    public ResponseEntity<PaginatedResult<InventoryResponse>> paginateInventoriesByWarehouse(@PathVariable UUID warehouseId,
            @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        PaginatedResult<InventoryResponse> inventory = paginateInventoriesByWarehouseUseCase
                .execute(new GetInventoriesByWarehouseCommand(warehouseId, new PageRequest(page, size)));
        return ResponseEntity.ok(inventory);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inventory> getInventoryById(@PathVariable UUID id) {
        Inventory inventory = findInventoryByIdUseCase.execute(new GetInventoryByIdCommand(id));
        return ResponseEntity.ok(inventory);
    }

    @PostMapping
    public ResponseEntity<Inventory> createInventory(@RequestBody CreateInventoryCommand command) {
        Inventory inventory = createInventoryUseCase.execute(command);
        return ResponseEntity.created(URI.create("/api/v1/inventory/" + inventory.id().toString())).build();
    }

}
