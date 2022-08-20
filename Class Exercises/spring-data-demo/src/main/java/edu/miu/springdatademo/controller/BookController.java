package edu.miu.springdatademo.controller;

import edu.miu.springdatademo.entity.Book;
import edu.miu.springdatademo.service.BookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> findAll() {
        return bookService.findAll();
    }

    // "books?price=100&name=test"
    @GetMapping("filterByNameAndPrice")
    public List<Book> findByNameAndPice(@RequestParam String name, @RequestParam int price) {
        return bookService.findAllByIsbnAndPriceGreaterThan(name, price);
    }


}
