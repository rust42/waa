package com.product.ReviewApp.controller;

import com.product.ReviewApp.Dto.ProductDto;
import com.product.ReviewApp.service.CategoryProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories/{categoryId}/products")
public class CategoryProductsController {


    private final CategoryProductService productService;

    public CategoryProductsController(CategoryProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    List<ProductDto> findAll(@PathVariable int categoryId) {
        return productService.products(categoryId);
    }

    @PostMapping
    Optional<ProductDto> create(@PathVariable int categoryId, @RequestBody ProductDto productDto) {
        return productService.add(categoryId, productDto);
    }

    @GetMapping("/{id}")
    Optional<ProductDto> findById(@PathVariable int categoryId, @PathVariable int id) {
        return productService.product(categoryId, id);
    }

    @DeleteMapping("/{id}")
    void deleteById(@PathVariable int categoryId, @PathVariable int id) {
        productService.delete(categoryId, id);
    }

}
