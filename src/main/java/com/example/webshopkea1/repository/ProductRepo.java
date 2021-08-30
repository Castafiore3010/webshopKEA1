package com.example.webshopkea1.repository;

import com.example.webshopkea1.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepo {
    @Autowired
    JdbcTemplate template;




    public List<Product> fetchAllProducts() {
        return template.query("SELECT * FROM products", new BeanPropertyRowMapper<>(Product.class));
    }

    public Product fetchProductById(int id) {
        return template.queryForObject("SELECT * FROM products where id = ?", new BeanPropertyRowMapper<>(Product.class), id);
    }


    public int insertProduct(Product product) {
        String sql = "INSERT INTO products (name, price) VALUES ('"+product.getName()+"','"+product.getPrice()+"')";
        return template.update(sql);
    }

    public int updateProduct(Product product) {
        String sql = "UPDATE products SET name = '"+product.getName()+"', price = "+product.getPrice()+" WHERE id = ?";
        return template.update(sql, product.getId());
    }

    public int deleteProduct(int id) {
        String sql ="DELETE FROM products WHERE id = ?";
        return template.update(sql, id);
    }
}
