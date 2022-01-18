package com.remotemode.nternship3inventoryconsumer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "inventories")
public class Inventory {
    @Id
    @Column(name = "inventory_id")
    private String itemId;
    @Column(name = "product_type")
    @Enumerated(EnumType.STRING)
    private ProductType productType;
    @Column(name = "product_title")
    private String title;
    @Column(name = "vendor")
    private String vendor;
    @Column(name = "price")
    private Double price;
    @Column(name = "barcode")
    private String barcode;
    @Column(name = "date")
    private Long accountDate;

    @OneToOne(mappedBy = "inventory")
    private AssignedInventory assignedInventory;
}
