package edu.miu.springdatademo.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.repository.cdi.Eager;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    private String title;

    private String isbn;

    private float price;

    @JsonManagedReference
    @ManyToOne
//    @Fetch(FetchMode.JOIN)
    private Publisher publisher;

    @ManyToMany
    private List<Author> authors;
}
