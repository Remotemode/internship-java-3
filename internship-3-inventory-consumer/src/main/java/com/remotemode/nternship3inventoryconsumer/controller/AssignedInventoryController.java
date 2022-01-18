package com.remotemode.nternship3inventoryconsumer.controller;

import com.remotemode.nternship3inventoryconsumer.dto.AssignedInventoryDto;
import com.remotemode.nternship3inventoryconsumer.model.AssignedInventory;
import com.remotemode.nternship3inventoryconsumer.service.IAssignedInventoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/v1/inventory")
public class AssignedInventoryController {
    private final IAssignedInventoryService assignedInventoryService;

    public AssignedInventoryController(IAssignedInventoryService assignedInventoryService) {
        this.assignedInventoryService = assignedInventoryService;
    }

    @PostMapping(value = "assign")
    public ResponseEntity<AssignedInventoryDto> assignInventoryToUser(@RequestParam(value = "userId") Long userId,
                                                                      @RequestParam(value = "inventoryId") String invetoryId) {
        AssignedInventory assignedInventory = assignedInventoryService.assignInventoryToUser(userId, invetoryId);
        return new ResponseEntity<>(AssignedInventoryDto.of(assignedInventory), HttpStatus.CREATED);
    }

    @GetMapping(value = "/barcode")
    public ResponseEntity<AssignedInventoryDto> getInventoryByBarcode(@RequestParam(value = "barcode") String barcode) {
        Optional<AssignedInventory> inventoryByBarcode = assignedInventoryService.findInventoryByBarcode(barcode);
        if (!inventoryByBarcode.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(AssignedInventoryDto.of(inventoryByBarcode.get()), HttpStatus.OK);
    }
}