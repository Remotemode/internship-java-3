package com.remotemode.nternship3inventoryconsumer.repository;

import com.remotemode.nternship3inventoryconsumer.model.AssignedInventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AssignedInventoryRepository extends JpaRepository<AssignedInventory, Long> {
    Optional<AssignedInventory> findAssignedInventoryByBarcode(String barcode);
}