package com.edu.lab6.service;

import com.edu.lab6.Dto.ProductDto;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<ProductDto> findAll();
    Optional<ProductDto> findById(int id);
    ProductDto save(ProductDto dto);
    void delete(int id);
}
