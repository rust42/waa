package com.edu.lab6.service.impl;

import com.edu.lab6.Dto.CategoryDto;
import com.edu.lab6.entity.Category;
import com.edu.lab6.repository.CategoryRepo;
import com.edu.lab6.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepo categoryRepo;

    private final ModelMapper mapper;

    public CategoryServiceImpl(CategoryRepo repo, ModelMapper mapper) {
        this.categoryRepo = repo;
        this.mapper = mapper;
    }

    @Override
    public List<CategoryDto> findAll() {
        List<Category> categories = new ArrayList<>();
        categoryRepo.findAll().forEach(categories::add);
        System.out.println(categories);
        return categories.stream()
                .map(this::categoryDto)
                .toList();
    }

    @Override
    public Optional<CategoryDto> findById(int id) {
        return categoryRepo.findById(id)
                .map(this::categoryDto);
    }

    @Override
    public CategoryDto save(CategoryDto dto) {
        var mapped = mapper.map(dto, Category.class);
        var saved = categoryRepo.save(mapped);
        return mapper.map(saved, CategoryDto.class);
    }

    @Override
    public void delete(int id) {
        categoryRepo.deleteById(id);
    }

    CategoryDto categoryDto(Category category) {
        return mapper.map(category, CategoryDto.class);
    }
}
