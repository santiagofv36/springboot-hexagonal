package com.santiago.hexagonal.modules.warehouse.infrastructure.entity;

import java.util.List;
import java.util.UUID;

import com.santiago.hexagonal.modules.inventory.infrastructure.entity.InventoryEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "warehouses")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WarehouseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "name", unique = true, nullable = false)
    private String name;
    @Column(name = "location", nullable = false)
    private String location;

    @OneToMany(mappedBy = "warehouse")
    private List<InventoryEntity> inventories;

    public WarehouseEntity(UUID id) {
        this.id = id;
    }
}
