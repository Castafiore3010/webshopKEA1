package com.example.webshopkea1.repository;

import com.example.webshopkea1.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProductRepositoryJpa extends JpaRepository<Product, Integer> {
    Optional<Product> findProductByName(String name);
}
