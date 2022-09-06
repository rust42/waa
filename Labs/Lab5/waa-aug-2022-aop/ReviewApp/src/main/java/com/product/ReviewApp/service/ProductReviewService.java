package com.product.ReviewApp.service;


import com.product.ReviewApp.Dto.ReviewDto;
import com.product.ReviewApp.entity.Review;

import java.util.List;
import java.util.Optional;

public interface ProductReviewService {
        List<ReviewDto> reviews(int productId);
        Optional<ReviewDto> add(int productId, ReviewDto reviewDto);

        Optional<ReviewDto> review(int productId, int reviewId);

        void delete(int productId, int reviewId);
}
