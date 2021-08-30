package com.example.webshopkea1.service;

import com.example.webshopkea1.model.Product;
import com.example.webshopkea1.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepo productRepo;


    public List<Product> fetchAllProducts() { return productRepo.fetchAllProducts(); }

    public Product fetchProductById(int id) { return productRepo.fetchProductById(id); }


    public int insertProduct(Product product)  { return productRepo.insertProduct(product); }

    public int deleteProduct(int id) { return productRepo.deleteProduct(id); }

    public int updateProduct(Product product) { return productRepo.updateProduct(product); }
}
