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

    public Optional<Product> findByName(String name) {return productRepositoryJpa.findProductByName(name);}


    public List<Product> fetchAllProducts() { return productRepo.fetchAllProducts(); } // jdbc

    public List<Product> fetchAllJpa() { // jpa
        return new ArrayList<>(productRepositoryJpa.findAll());

    }

    public Product fetchProductById(int id) { return productRepo.fetchProductById(id); } //jdbc

    public Optional<Product> findProductByIdJpa(int id) {
        return productRepositoryJpa.findById(id);
    }


    public int insertProduct(Product product)  { return productRepo.insertProduct(product); }//jdbc

    public void insertProductJpa(Product product) { // jpa
        productRepositoryJpa.save(product);
    }

    public int deleteProduct(int id) { return productRepo.deleteProduct(id); } //jdbc

    public void deleteProductJpa(int id) { // jpa
        productRepositoryJpa.deleteById(id);
    }

    public int updateProduct(Product product) { return productRepo.updateProduct(product); }//jdbc

    public void updateProductJpa(Product product) { // jpa
        productRepositoryJpa.save(product);
    }
}
