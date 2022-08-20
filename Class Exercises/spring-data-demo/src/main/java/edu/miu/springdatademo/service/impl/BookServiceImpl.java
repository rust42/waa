package edu.miu.springdatademo.service.impl;

import edu.miu.springdatademo.entity.Book;
import edu.miu.springdatademo.repository.BookRepository;
import edu.miu.springdatademo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository repository;

    @Override
    public List<Book> findAll() {
        var list = new ArrayList<Book>();
        repository.findAll().forEach(list::add);
        return list;
    }

    @Override
    public List<Book> findAllByIsbnAndPriceGreaterThan(String isbn, float price) {
        return repository.findAllByIsbnAndPriceGreaterThan(isbn, price);
    }

}
