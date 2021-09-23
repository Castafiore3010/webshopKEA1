package com.example.webshopkea1.repository;

import com.example.webshopkea1.model.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewRepo extends JpaRepository<Review, Long> {
    Page<Review> findByProductId(Integer productId, Pageable pageable);
    Optional<Review> findByIdAndProductId(Long id, Integer product_id);
}
