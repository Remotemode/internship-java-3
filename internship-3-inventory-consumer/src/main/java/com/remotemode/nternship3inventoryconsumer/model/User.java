package com.remotemode.nternship3inventoryconsumer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "second_name")
    private String secondName;

    @OneToMany(mappedBy="assignedUser")
    private Set<AssignedInventory> items;

    public User(String firstName, String secondName) {
        this.firstName = firstName;
        this.secondName = secondName;
    }
}
