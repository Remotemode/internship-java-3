package com.remotemode.nternship3inventoryconsumer.service;

import com.remotemode.nternship3inventoryconsumer.model.Inventory;
import com.remotemode.nternship3inventoryconsumer.repository.InventoryRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class InventoryService implements IInventoryService {
    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public void save(Inventory inventory) {
        inventoryRepository.save(inventory);
    }

    @Override
    public List<Inventory> findAll() {
        return inventoryRepository.findAll();
    }

    @Override
    public Optional<Inventory> findInventoryById(String inventoryId) {
        return inventoryRepository.findById(inventoryId);
    }
}
