package com.main.micvroservice.inventory_service.repository;

import com.main.micvroservice.inventory_service.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository  extends JpaRepository<Inventory, Long> {

    boolean existsBySkuCodeAndQuantityIsGreaterThanEqual(String skuCode, Integer quantity);
}
