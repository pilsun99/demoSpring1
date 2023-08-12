package com.example.demo.service;

import com.example.demo.entity.CartItem;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
@SessionScope
@Service

public class ShoppingCartServiceImpl implements ShoppingCartService, Serializable {
    private static final long serialVersionUID = 1L;
    Map<Integer, CartItem> maps = new HashMap<>();// giỏ hàng

    @Override
    public void add(CartItem item){
        CartItem cartItem = maps.get(item.getProductId());
        if (cartItem == null){
            maps.put(item.getProductId(), item);
        }else{
            cartItem.setQuantity(cartItem.getQuantity()+1);
        }
    }

    @Override
    public void remove(int id){
        maps.remove(id);
    }

    @Override
    public CartItem update(int proID, int quantity){
        CartItem cartItem = maps.get(proID);
        cartItem.setQuantity(quantity);
        return cartItem;
    }

    @Override
    public void  clear(){
        maps.clear();
    }

    @Override
    public Collection<CartItem> getAllItems(){
        return maps.values();
    }

    @Override
    public int getCount(){
        return maps.values().size();
    }

    @Override
    public double getAmount(){
        return maps.values().stream().mapToDouble(item -> item.getQuantity() * item.getPrice()).sum();
    }
}
