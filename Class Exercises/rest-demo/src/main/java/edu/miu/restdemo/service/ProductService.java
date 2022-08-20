package edu.miu.restdemo.service;

import edu.miu.restdemo.dto.ProductDto;

import java.util.List;

public interface ProductService {

    List<ProductDto> findAll();
    void save(ProductDto productDto);
}
