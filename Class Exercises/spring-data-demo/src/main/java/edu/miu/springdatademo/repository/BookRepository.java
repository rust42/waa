package edu.miu.springdatademo.repository;

import edu.miu.springdatademo.entity.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {
    List<Book> findAllByIsbnAndPriceGreaterThan(String isbn, float price);
}
