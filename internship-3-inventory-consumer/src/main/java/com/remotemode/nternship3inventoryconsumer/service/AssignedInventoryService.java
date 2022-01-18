package com.remotemode.nternship3inventoryconsumer.service;

import com.remotemode.nternship3inventoryconsumer.model.AssignedInventory;
import com.remotemode.nternship3inventoryconsumer.model.Inventory;
import com.remotemode.nternship3inventoryconsumer.model.User;
import com.remotemode.nternship3inventoryconsumer.repository.AssignedInventoryRepository;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.UUID;

@Component
public class AssignedInventoryService implements IAssignedInventoryService {
    private final AssignedInventoryRepository assignedInventoryRepository;
    private final IInventoryService inventoryService;
    private final IUserService userService;

    public AssignedInventoryService(AssignedInventoryRepository assignedInventoryRepository, IInventoryService inventoryService, IUserService userService) {
        this.assignedInventoryRepository = assignedInventoryRepository;
        this.inventoryService = inventoryService;
        this.userService = userService;
    }

    @Override
    public AssignedInventory assignInventoryToUser(Long userId, String inventoryId) {
        User user = userService.findUserById(userId)
                .orElseThrow(() -> new RuntimeException("No User found for userId" + userId.toString()));

        Inventory inventory = inventoryService.findInventoryById(inventoryId)
                .orElseThrow(() -> new RuntimeException("No inventory found for inventoryId" + inventoryId));

        AssignedInventory assignedInventory = new AssignedInventory();
        assignedInventory.setInventory(inventory);
        assignedInventory.setAssignedUser(user);
        assignedInventory.setUseTime(new Timestamp(System.currentTimeMillis()));
        assignedInventory.setBarcode(UUID.randomUUID().toString());

        return assignedInventoryRepository.save(assignedInventory);
    }

    @Override
    public Optional<AssignedInventory> findInventoryByBarcode(String barcode) {
        return assignedInventoryRepository.findAssignedInventoryByBarcode(barcode);
    }
}
