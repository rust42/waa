package com.edu.lab6.controller;

import com.edu.lab6.Dto.CategoryDto;
import com.edu.lab6.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    CategoryDto create(@RequestBody CategoryDto categoryDto) {
         return categoryService.save(categoryDto);
    }

    @GetMapping
    List<CategoryDto> allCategories() {
        return categoryService.findAll();
    }

    @GetMapping("/{id}")
    Optional<CategoryDto> find(@PathVariable int id) {
        return categoryService.findById(id);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable int id) {
        categoryService.delete(id);
    }
}
