package com.remotemode.nternship3inventoryconsumer.service;

import com.remotemode.nternship3inventoryconsumer.model.Inventory;

import java.util.List;

public interface IInventoryService {
    void save(Inventory inventory);
    List<Inventory> findAll();
    Inventory findInventoryByBarcode(String barcode);

}
