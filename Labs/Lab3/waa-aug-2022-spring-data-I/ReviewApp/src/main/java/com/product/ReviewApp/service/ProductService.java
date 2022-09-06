package com.product.ReviewApp.service;

import com.product.ReviewApp.Dto.ProductDto;

import java.util.List;
import java.util.Optional;



public interface ProductService {
    List<ProductDto> findAll();

    Optional<ProductDto> findById(int id);

    ProductDto save(ProductDto dto);

    void delete(int id);

    List<ProductDto> filterByMinPrice(float minPrice);

    List<ProductDto> filterByName(String keyword);

    List<ProductDto> filterByCategoryNameAndMaxPrice(String categoryName, float maxPrice);

    List<ProductDto> filterByMinPrice(float minPrice, float maxPrice, String keyword, String categoryName);
}
