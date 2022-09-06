package com.product.ReviewApp.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;


@Entity
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(unique = true)
    private String name;


    @JsonManagedReference
    @OneToMany(cascade = CascadeType.MERGE,
            mappedBy = "category",
            orphanRemoval = true)
    private List<Product> products;

    public void addProduct(Product product) {
        products.add(product);
        product.setCategory(this);
    }
}
