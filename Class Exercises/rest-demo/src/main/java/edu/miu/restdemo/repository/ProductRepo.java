package edu.miu.restdemo.repository;

import edu.miu.restdemo.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ProductRepo {

    private static List<Product> products = new ArrayList<>();

    public List<Product> findAll() {
        var result = products
                .stream()
                .filter(l -> !l.isDeleted())
                .collect(Collectors.toList());
        return products;
    }

    public void save(Product product) {
        products.add(product);
    }



}
