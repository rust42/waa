package edu.miu.springdatademo.service;

import edu.miu.springdatademo.entity.Book;

import java.util.List;

public interface BookService {
    List<Book> findAll();
    List<Book> findAllByIsbnAndPriceGreaterThan(String isbn, float price);

}
