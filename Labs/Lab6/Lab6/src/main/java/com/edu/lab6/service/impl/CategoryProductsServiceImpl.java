package com.edu.lab6.service.impl;

import com.edu.lab6.Dto.ProductDto;
import com.edu.lab6.entity.Category;
import com.edu.lab6.entity.Product;
import com.edu.lab6.repository.CategoryRepo;
import com.edu.lab6.repository.ProductRepo;
import com.edu.lab6.service.CategoryProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryProductsServiceImpl implements CategoryProductService {
    private final CategoryRepo categoryRepo;
    private final ProductRepo productRepo;

    private final ModelMapper mapper;

    public CategoryProductsServiceImpl(CategoryRepo categoryRepo, ProductRepo productRepo, ModelMapper mapper) {
        this.categoryRepo = categoryRepo;
        this.productRepo = productRepo;
        this.mapper = mapper;
    }

    @Override
    public List<ProductDto> products(int categoryId) {
        return categoryRepo.findById(categoryId)
                .map(Category::getProducts)
                .map(products -> products
                        .stream()
                        .map(product -> mapper
                                .map(product, ProductDto.class))
                        .toList())
                .orElseGet(ArrayList::new);
    }

    @Override
    public Optional<ProductDto> add(int categoryId, ProductDto productDto) {
        Optional<Category> category = categoryRepo.findById(categoryId);

        if (category.isPresent()) {
            Product product = mapper.map(productDto, Product.class);
            category.get().addProduct(product);
            product = productRepo.save(product);
            return Optional.of(mapper.map(product, ProductDto.class));
        }

        return Optional.empty();

    }

    @Override
    public Optional<ProductDto> product(int categoryId, int productId) {
        return categoryRepo.findById(categoryId)
                .flatMap(category ->
                        category.getProducts().stream()
                            .filter(product -> product.getId() == productId).findFirst())
                .map(product -> mapper.map(product, ProductDto.class));
    }

    @Override
    public void delete(int categoryId, int productId) {
         categoryRepo.findById(categoryId)
                .flatMap(category ->
                        category.getProducts().stream()
                                .filter(product -> product.getId() == productId).findFirst())
                .ifPresent(productRepo::delete);
    }
}
