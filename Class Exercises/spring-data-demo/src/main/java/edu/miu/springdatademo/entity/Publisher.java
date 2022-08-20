package edu.miu.springdatademo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    private String name;
    private String zipCode;

    @JsonBackReference
    @OneToMany(mappedBy = "publisher")
//    @JoinColumn(name = "id_publisher")
    private List<Book> books;
}
