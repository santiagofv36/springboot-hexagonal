package com.santiago.hexagonal.modules.product.infrastructure.entity;

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
@Table(name = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "sku", nullable = false)
    private String sku;

    @OneToMany(mappedBy = "product")
    private List<InventoryEntity> inventories;

    public ProductEntity(UUID id) {
        this.id = id;
    }
}
