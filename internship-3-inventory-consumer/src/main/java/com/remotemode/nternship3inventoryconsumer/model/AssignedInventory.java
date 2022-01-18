package com.remotemode.nternship3inventoryconsumer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "inventory_items")
public class AssignedInventory {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "assigned_inventory_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User assignedUser;

    @Column(name = "barcode")
    private String barcode;

    @Column(name = "use_time", nullable = false)
    private Timestamp useTime;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "inventory_id", referencedColumnName = "inventory_id")
    private Inventory inventory;
}
