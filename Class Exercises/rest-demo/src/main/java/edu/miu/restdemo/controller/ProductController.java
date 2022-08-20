package edu.miu.restdemo.controller;


import edu.miu.restdemo.dto.ProductDto;
import edu.miu.restdemo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public List<ProductDto> findAll() {
        return productService.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/products")
    public void save(@RequestBody ProductDto dto) {
//        ResponseEntity.status(HttpStatus.CREATED)
        productService.save(dto);
    }

    @GetMapping("/products/{id}")
    public ProductDto findById(@PathVariable int id) {
        return null;
    }

    @PutMapping("/{id}")
    public void update(@PathVariable int id, @RequestBody ProductDto dto) {
    }
}
