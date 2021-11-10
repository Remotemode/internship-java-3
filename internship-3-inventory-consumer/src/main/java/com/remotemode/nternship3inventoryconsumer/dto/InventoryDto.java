package com.remotemode.nternship3inventoryconsumer.dto;

import com.remotemode.nternship3inventoryconsumer.model.Inventory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class InventoryDto {
    private String itemId;
    private String productType;
    private String title;
    private String vendor;
    private Double price;
    private String barcode;
    private String accountDate;

    public static InventoryDto of(Inventory inventory) {
        return new InventoryDto(inventory.getItemId(), inventory.getProductType().getProductType(), inventory.getTitle(),
                inventory.getVendor(),
                inventory.getPrice(),
                inventory.getBarcode(),
                toDate(inventory.getAccountDate()));
    }

    private static String toDate(Long timestamp) {
        Date date = new Date(timestamp);
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(date);
    }
}

