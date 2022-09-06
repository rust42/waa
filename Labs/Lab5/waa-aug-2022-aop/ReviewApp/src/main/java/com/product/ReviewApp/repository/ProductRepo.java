package com.product.ReviewApp.repository;

import com.product.ReviewApp.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepo extends CrudRepository<Product, Integer> {
    
}
