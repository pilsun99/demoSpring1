package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItem implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer productId;
    private String name;
    private double price;
    private int quantity = 1;

}
