package com.edu.lab6.service;


import com.edu.lab6.Dto.ReviewDto;
import com.edu.lab6.entity.Review;

import java.util.List;
import java.util.Optional;

public interface ProductReviewService {
        List<ReviewDto> reviews(int productId);
        Optional<ReviewDto> add(int productId, ReviewDto reviewDto);

        Optional<ReviewDto> review(int productId, int reviewId);

        void delete(int productId, int reviewId);
}
