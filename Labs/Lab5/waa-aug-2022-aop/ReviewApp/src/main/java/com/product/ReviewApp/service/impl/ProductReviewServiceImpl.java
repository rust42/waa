package com.product.ReviewApp.service.impl;

import com.product.ReviewApp.Dto.ReviewDto;
import com.product.ReviewApp.entity.Product;
import com.product.ReviewApp.entity.Review;
import com.product.ReviewApp.repository.ProductRepo;
import com.product.ReviewApp.repository.ReviewRepo;
import com.product.ReviewApp.service.ProductReviewService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductReviewServiceImpl implements ProductReviewService  {

    private final ProductRepo productRepo;
    private final ReviewRepo reviewRepo;

    private final ModelMapper mapper;

    public ProductReviewServiceImpl(ProductRepo productRepo, ReviewRepo reviewRepo, ModelMapper mapper) {
        this.productRepo = productRepo;
        this.reviewRepo = reviewRepo;
        this.mapper = mapper;
    }

    @Override
    public List<ReviewDto> reviews(int productId) {
        return productRepo.findById(productId)
                .map(Product::getReviews)
                .map(reviews -> reviews
                        .stream()
                        .map(review -> mapper.map(review, ReviewDto.class))
                            .toList())
                .orElseGet(ArrayList::new);

    }

    @Override
    public Optional<ReviewDto> add(int productId, ReviewDto reviewDto) {
        Optional<Product> product = productRepo.findById(productId);

        if (product.isPresent()) {
            Review review = mapper.map(reviewDto, Review.class);
            product.get().addReview(review);
            review = reviewRepo.save(review);
            return Optional.of(mapper.map(reviewRepo.findById(review.getId()), ReviewDto.class));
        }

        return Optional.empty();
    }

    @Override
    public Optional<ReviewDto> review(int productId, int reviewId) {
        return productRepo.findById(productId)
                .flatMap(product ->
                        product.getReviews().stream()
                                .filter(review -> review.getId() == reviewId).findFirst())
                .map(review -> mapper.map(review, ReviewDto.class));
    }

    @Override
    public void delete(int productId, int reviewId) {
        productRepo.findById(productId)
                .flatMap(product ->
                        product.getReviews().stream()
                                .filter(review -> review.getId() == reviewId).findFirst())
                .ifPresent(reviewRepo::delete);
    }
}
