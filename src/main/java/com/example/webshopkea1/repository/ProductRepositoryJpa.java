package com.example.webshopkea1.repository;

import com.example.webshopkea1.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepositoryJpa extends JpaRepository<Product, Integer> {
}
