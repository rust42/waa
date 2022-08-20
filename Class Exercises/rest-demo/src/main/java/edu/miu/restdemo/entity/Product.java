package edu.miu.restdemo.entity;

import lombok.Data;

@Data
public class Product {
    private String name;
    private int price;
    private String category;

    private boolean deleted;
}
