package com.product.ReviewApp.repository;

import com.product.ReviewApp.entity.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoryRepo extends CrudRepository<Category, Integer> {
}
