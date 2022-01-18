package com.remotemode.nternship3inventoryconsumer.service;

import com.remotemode.nternship3inventoryconsumer.model.AssignedInventory;

import java.util.Optional;

public interface IAssignedInventoryService {
    AssignedInventory assignInventoryToUser(Long userId, String inventoryId);

    Optional<AssignedInventory> findInventoryByBarcode(String barcode);
}