package com.spring.rest.phase1.service;

import com.spring.rest.phase1.entity.Course;

import java.util.List;
import java.util.Optional;

public interface ItemService<T> {
    List<T> findAll();
    Optional<T> find(int id);
    T save(T item);
    void delete(int id);
}
