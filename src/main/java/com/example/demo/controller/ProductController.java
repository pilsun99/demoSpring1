package com.example.demo.controller;

import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("product")
public class ProductController {
    @Autowired
    ProductService service;
    @GetMapping("views")
    public String viewProduct(Model model){
        model.addAttribute("LIST_PRODUCT", service.getAll());
        return "shopping-cart";
    }
}
