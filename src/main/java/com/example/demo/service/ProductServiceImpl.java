package com.example.demo.service;

import com.example.demo.entity.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class ProductServiceImpl implements ProductService{
    public static List<Product> list = new ArrayList<>();
    static {
        list.add(new Product(1,"Iphone5", "", 1100, new Date(), true, ""));
        list.add(new Product(2,"Iphone6", "", 2100, new Date(), true, ""));
        list.add(new Product(3,"Iphone7", "", 3100, new Date(), true, ""));
        list.add(new Product(4,"Iphone8", "", 4100, new Date(), true, ""));
    }
    @Override
    public int add(Product product) {
        return 0;
    }

    @Override
    public int remove(int id) {
        return 0;
    }

    @Override
    public List<Product> getAll() {
        return list;
    }

    @Override
    public Product findProductById(int id) {
        for (Product product: list
             ) {
            if (product.getId() == id){
                return product;
            }
        }
        return null;
    }
}
