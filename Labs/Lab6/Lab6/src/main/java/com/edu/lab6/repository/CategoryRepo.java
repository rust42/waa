package com.edu.lab6.repository;

import com.edu.lab6.entity.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoryRepo extends CrudRepository<Category, Integer> {
}
