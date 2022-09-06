package com.product.ReviewApp.service.impl;

import com.product.ReviewApp.Dto.ProductDto;
import com.product.ReviewApp.entity.Product;
import com.product.ReviewApp.repository.ProductRepo;
import com.product.ReviewApp.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepo;
    private final ModelMapper modelMapper;

    public ProductServiceImpl(ProductRepo productRepo, ModelMapper modelMapper) {
        this.productRepo = productRepo;
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

    @Override
    public List<ProductDto> filterByMinPrice(float minPrice) {
        return productRepo.findAllByPriceGreaterThanEqual(minPrice)
                .stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .toList();
    }

    @Override
    public List<ProductDto> filterByName(String keyword) {
        return productRepo.findAllByNameContaining(keyword)
                .stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .toList();

    }

    @Override
    public List<ProductDto> filterByCategoryNameAndMaxPrice(String categoryName, float maxPrice) {
        return productRepo.findAllByCategoryNameAndPriceLessThanEqual(categoryName, maxPrice)
                .stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .toList();

    }

    @Override
    public List<ProductDto> filterByMinPrice(float minPrice, float maxPrice, String keyword, String categoryName) {
        return productRepo.findAllByPriceGreaterThanEqualAndPriceLessThanEqualAndNameContainingIgnoreCaseAndCategoryNameContaining(minPrice, maxPrice, keyword, categoryName)
                .stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .toList();
    }

}
