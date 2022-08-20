package edu.miu.springdatademo.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    private String city;
    private String state;

    @OneToOne(mappedBy = "address")
    private Author author;
}
