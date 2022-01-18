package com.remotemode.nternship3inventoryconsumer.service;

import com.remotemode.nternship3inventoryconsumer.model.Inventory;

import java.util.List;
import java.util.Optional;

public interface IInventoryService {
    void save(Inventory inventory);
    List<Inventory> findAll();
    Optional<Inventory> findInventoryById(String inventoryId);
}
