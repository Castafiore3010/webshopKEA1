package com.example.webshopkea1.service;

import com.example.webshopkea1.model.Product;
import com.example.webshopkea1.repository.ProductRepo;
import com.example.webshopkea1.repository.ProductRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepo productRepo;
    @Autowired
    ProductRepositoryJpa productRepositoryJpa;


    public List<Product> fetchAllProducts() { return productRepo.fetchAllProducts(); }

    public List<Product> fetchAllJpa() {
        return new ArrayList<>(productRepositoryJpa.findAll());

    }

    public Product fetchProductById(int id) { return productRepo.fetchProductById(id); }

    public Optional<Product> findProductByIdJpa(int id) {
        return productRepositoryJpa.findById(id);
    }


    public int insertProduct(Product product)  { return productRepo.insertProduct(product); }

    public void insertProductJpa(Product product) {
        productRepositoryJpa.save(product);
    }

    public int deleteProduct(int id) { return productRepo.deleteProduct(id); }

    public void deleteProductJpa(int id) {
        productRepositoryJpa.deleteById(id);
    }

    public int updateProduct(Product product) { return productRepo.updateProduct(product); }

    public void updateProductJpa(Product product) {
        productRepositoryJpa.save(product);
    }
}
