package com.remotemode.nternship3inventoryconsumer.dto;

import com.remotemode.nternship3inventoryconsumer.model.AssignedInventory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
public class AssignedInventoryDto {
    private String inventoryId;
    private Long userId;
    private String barcode;
    private Timestamp useTime;

    public static AssignedInventoryDto of(AssignedInventory assignedInventory) {
        return new AssignedInventoryDto(assignedInventory.getInventory().getItemId(),
                assignedInventory.getAssignedUser().getId(),
                assignedInventory.getBarcode(),
                assignedInventory.getUseTime());
    }
}