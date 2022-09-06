package com.product.ReviewApp.Dto;

import lombok.Data;

import java.util.List;

@Data
public class CategoryDto {
    private int id;
    private String name;

    private List<ProductDto> products;

    @Override
    public String toString() {
        return id + name;
    }
}
