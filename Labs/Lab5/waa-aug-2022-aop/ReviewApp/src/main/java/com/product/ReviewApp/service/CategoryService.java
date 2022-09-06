package com.product.ReviewApp.service;

import com.product.ReviewApp.Dto.CategoryDto;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<CategoryDto> findAll();
    Optional<CategoryDto> findById(int id);
    CategoryDto save(CategoryDto dto);
    void delete(int id);
}
