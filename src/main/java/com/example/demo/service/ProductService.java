package com.example.demo.service;

import com.example.demo.entity.Product;

import java.util.List;

public interface ProductService {
    public int add(Product product);
    public int remove(int id);
    public List<Product> getAll();
    public Product findProductById(int id);
}
