package com.example.demo.service;

import com.example.demo.entity.CartItem;

import java.util.Collection;

public interface ShoppingCartService {

    void add(CartItem item);

    void remove(int id);

    CartItem update(int proID, int quantity);

    void  clear();

    Collection<CartItem> getAllItems();

    int getCount();

    double getAmount();
}
