package com.remotemode.nternship3inventoryconsumer.controller;

import com.remotemode.nternship3inventoryconsumer.dto.InventoryDto;
import com.remotemode.nternship3inventoryconsumer.model.Inventory;
import com.remotemode.nternship3inventoryconsumer.service.IInventoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/inventory")
public class InventoryController {
    private final IInventoryService inventoryService;

    public InventoryController(IInventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping
    public ResponseEntity<List<InventoryDto>> getAllInventories() {
        List<Inventory> inventories = inventoryService.findAll();
        if (inventories.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<InventoryDto> inventoryDtos = inventories.stream().map(InventoryDto::of).
                collect(Collectors.toList());
        return new ResponseEntity<>(inventoryDtos, HttpStatus.OK);
    }

    @GetMapping(value = "/barcode")

    public ResponseEntity<InventoryDto> getInventoryByBarcode(@RequestParam(value = "barcode") String barcode) {
        Inventory inventoryByBarcode = inventoryService.findInventoryByBarcode(barcode);
        if (Objects.isNull(inventoryByBarcode)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(InventoryDto.of(inventoryByBarcode), HttpStatus.OK);
    }
}

