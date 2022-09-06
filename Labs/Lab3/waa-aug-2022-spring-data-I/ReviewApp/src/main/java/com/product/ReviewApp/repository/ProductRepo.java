package com.product.ReviewApp.repository;

import com.product.ReviewApp.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends CrudRepository<Product, Integer> {
    List<Product> findAllByPriceGreaterThanEqual(float minPrice);
    List<Product> findAllByNameContaining(String keyword);
    List<Product> findAllByCategoryNameAndPriceLessThanEqual(String categoryName, float maxPrice);
    List<Product> findAllByPriceGreaterThanEqualAndPriceLessThanEqualAndNameContainingIgnoreCaseAndCategoryNameContaining(float minPrice, float maxPrice, String keyWord, String categoryName);


}
