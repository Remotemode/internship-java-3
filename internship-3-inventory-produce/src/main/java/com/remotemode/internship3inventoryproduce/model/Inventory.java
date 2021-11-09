package com.remotemode.internship3inventoryproduce.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Inventory {
    private String itemId;
    private ProductType productType;
    private String title;
    private String vendor;
    private Double price;
    private String barcode;
    private Long accountDate;
}
