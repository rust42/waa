package com.edu.lab6.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(unique = true)
    private String email;

    private String password;
    private String firstName;
    private String lastName;

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    @JsonBackReference
    List<Review> reviews;

    @OneToOne(mappedBy = "user", cascade = CascadeType.MERGE, orphanRemoval = true)
    @JsonManagedReference
    Address address;

    @ManyToMany
    private Set<Role> roles;

    public void addRole(Role role) {
        this.roles.add(role);
    }
}
