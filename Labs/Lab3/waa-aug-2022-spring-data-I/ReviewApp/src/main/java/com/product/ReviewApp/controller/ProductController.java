package com.product.ReviewApp.controller;

import com.product.ReviewApp.Dto.ProductDto;
import com.product.ReviewApp.entity.Product;
import com.product.ReviewApp.repository.ProductRepo;
import com.product.ReviewApp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    List<ProductDto> all(@RequestParam Map<String, String> params) {
        String minPriceString =  params.getOrDefault("minPrice", "0");
        String keyword = params.getOrDefault("keyword", "");
        String categoryName = params.getOrDefault("categoryName", "");
        String maxPriceString = params.getOrDefault("maxPrice",  Float.toString(Float.MAX_VALUE));

        float minPrice = Float.parseFloat(minPriceString);
        float maxPrice = Float.parseFloat(maxPriceString);

        return productService.filterByMinPrice(minPrice, maxPrice, categoryName, keyword);

    }

//    @GetMapping
//    List<ProductDto> filterProducts(@RequestParam float minPrice) {
//        return productService.filterByMinPrice(minPrice);
//    }
//
//    @GetMapping
//    List<ProductDto> filterProductsByKeyword(@RequestParam String keyword) {
//        return productService.filterByName(keyword);
//    }
//
//    @GetMapping
//    List<ProductDto> filterProductsByCategoryNameAndMaxPrice(@RequestParam String categoryName, @RequestParam float maxPrice) {
//        return productService.filterByCategoryNameAndMaxPrice(categoryName, maxPrice);
//    }
}
