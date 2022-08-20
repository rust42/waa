package edu.miu.springdatademo.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "writers")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(name = "author_name")
    private String name;

    private int age;

    @ManyToMany(mappedBy = "authors")
    private List<Book> books;


    @OneToOne
    private Address address;
}
