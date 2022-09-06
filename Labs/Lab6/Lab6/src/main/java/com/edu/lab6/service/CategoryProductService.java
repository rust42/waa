package com.edu.lab6.service;

import com.edu.lab6.Dto.ProductDto;

import java.util.List;
import java.util.Optional;

public interface CategoryProductService  {
    List<ProductDto> products(int categoryId);
    Optional<ProductDto> add(int categoryId, ProductDto product);

    Optional<ProductDto> product(int categoryId, int productId);

    void delete(int categoryId, int productId);

}
