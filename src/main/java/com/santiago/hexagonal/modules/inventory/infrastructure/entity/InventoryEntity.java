package com.santiago.hexagonal.modules.inventory.infrastructure.entity;

import java.util.UUID;

import com.santiago.hexagonal.modules.product.infrastructure.entity.ProductEntity;
import com.santiago.hexagonal.modules.warehouse.infrastructure.entity.WarehouseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "inventories", uniqueConstraints = @UniqueConstraint(columnNames = { "product_id", "warehouse_id" }))
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "available_quantity", nullable = false)
    private int availableQuantity;

    // Relations
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "warehouse_id", nullable = false)
    private WarehouseEntity warehouse;

}
