package com.remotemode.nternship3inventoryconsumer.repository;

import com.remotemode.nternship3inventoryconsumer.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, String> {
    Optional<Inventory> findInventoryByBarcode(String barcode);
}
