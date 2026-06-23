package com.santiago.hexagonal.modules.warehouse.infrastructure;

import java.net.URI;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.santiago.hexagonal.domain.pagination.PageRequest;
import com.santiago.hexagonal.domain.pagination.PaginatedResult;
import com.santiago.hexagonal.application.interfaces.IApplicationService;
import com.santiago.hexagonal.modules.warehouse.application.port.in.CreateWarehouseCommand;
import com.santiago.hexagonal.modules.warehouse.application.port.in.GetWarehouseByIdCommand;
import com.santiago.hexagonal.modules.warehouse.application.port.in.GetWarehouseByNameCommand;
import com.santiago.hexagonal.modules.warehouse.domain.Warehouse;

@RestController
@RequestMapping("/api/v1/warehouses")
public class WarehouseController {

    @Autowired
    private IApplicationService<CreateWarehouseCommand, Warehouse> createWarehouseUseCase;
    @Autowired
    private IApplicationService<PageRequest, PaginatedResult<Warehouse>> findAllWarehousesUseCase;
    @Autowired
    private IApplicationService<GetWarehouseByIdCommand, Warehouse> findWarehouseByIdUseCase;
    @Autowired
    private IApplicationService<GetWarehouseByNameCommand, Warehouse> findWarehouseByNameUseCase;

    @PostMapping
    public ResponseEntity<Warehouse> createWarehouse(@RequestBody CreateWarehouseCommand command) {
        return ResponseEntity
                .created(URI.create("/api/v1/warehouses/" + createWarehouseUseCase.execute(command).id().toString()))
                .build();
    }

    @GetMapping
    public ResponseEntity<PaginatedResult<Warehouse>> findAllWarehouses(@RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int perPage) {
        return ResponseEntity.ok(findAllWarehousesUseCase.execute(new PageRequest(page, perPage)));
    }

    @GetMapping("/find-by-name")
    public ResponseEntity<Warehouse> findWarehouseByName(@RequestParam String name) {
        return ResponseEntity.ok(findWarehouseByNameUseCase.execute(new GetWarehouseByNameCommand(name)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Warehouse> findWarehouseById(@PathVariable UUID id) {
        return ResponseEntity.ok(findWarehouseByIdUseCase.execute(new GetWarehouseByIdCommand(id)));
    }

}
