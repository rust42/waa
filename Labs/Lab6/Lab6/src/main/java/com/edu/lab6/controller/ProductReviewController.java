package com.edu.lab6.controller;

import com.edu.lab6.Dto.ReviewDto;
import com.edu.lab6.service.ProductReviewService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products/{productId}/reviews")
public class ProductReviewController {

    private final ProductReviewService productReviewService;

    public ProductReviewController(ProductReviewService productReviewService) {
        this.productReviewService = productReviewService;
    }

    @GetMapping
    public List<ReviewDto> findAll(@PathVariable int productId) {
        return productReviewService.reviews(productId);
    }

    @PostMapping
    public Optional<ReviewDto> create(@PathVariable int productId, @RequestBody ReviewDto reviewDto) {
        return productReviewService.add(productId, reviewDto);
    }

    @GetMapping("/{reviewId}")
    public Optional<ReviewDto> find(@PathVariable int productId, @PathVariable int reviewId) {
        return productReviewService.review(productId, reviewId);
    }

    @DeleteMapping("/{reviewId}")
    public void delete(@PathVariable int productId, @PathVariable int reviewId) {
        productReviewService.delete(productId, reviewId);
    }
}
