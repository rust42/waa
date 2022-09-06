package com.edu.lab6.service.impl;

import com.edu.lab6.Dto.ProductDto;
import com.edu.lab6.entity.Product;
import com.edu.lab6.repository.CategoryRepo;
import com.edu.lab6.repository.ProductRepo;
import com.edu.lab6.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepo;
    private final CategoryRepo categoryRepo;
    private final ModelMapper modelMapper;

    public ProductServiceImpl(ProductRepo productRepo, CategoryRepo categoryRepo, ModelMapper modelMapper) {
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ProductDto> findAll() {
        List<Product> products = new ArrayList<>();

        productRepo.findAll()
                .forEach(products::add);
        return products.stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .toList();
    }

    @Override
    public Optional<ProductDto> findById(int id) {
        return productRepo.findById(id)
                .map(a -> modelMapper.map(a, ProductDto.class));
    }

    @Override
    public ProductDto save(ProductDto dto) {
        Product product = modelMapper.map(dto, Product.class);
        Product savedProduct = productRepo.save(product);
        return modelMapper.map(productRepo.findById(savedProduct.getId()), ProductDto.class);
    }

    @Override
    public void delete(int id) {
        productRepo.deleteById(id);
    }
}
