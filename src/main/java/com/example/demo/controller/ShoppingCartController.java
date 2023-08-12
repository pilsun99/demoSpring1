package com.example.demo.controller;

import com.example.demo.entity.CartItem;
import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;
import com.example.demo.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("shopping-cart")
public class ShoppingCartController {
    @Autowired
    ProductService productService;
    @Autowired
    ShoppingCartService shoppingCartService;
    @GetMapping("views")
    public String viewCart(Model model){
        model.addAttribute("CART_ITEMS", shoppingCartService.getAllItems());
        model.addAttribute("TOTAL", shoppingCartService.getAmount());
        return "cart-item";
    }

    @GetMapping("add/{id}")
    public String addCart(@PathVariable("id") Integer id){
        Product product = productService.findProductById(id);
        if (product!= null){
            CartItem item = new CartItem();
            item.setProductId(product.getId());
            item.setName(product.getName());
            item.setPrice(product.getPrice());
            item.setQuantity(1);
            shoppingCartService.add(item);
        }
        return "redirect:/shopping-cart/views";
    }

    @GetMapping("clear")
    public String clearCart(){
        shoppingCartService.clear();
        return "redirect:/shopping-cart/views";
    }

    @GetMapping("delete/{id}")
    public String removeCart(@PathVariable("id") Integer id){
        shoppingCartService.remove(id);
        return "redirect:/shopping-cart/views";
    }

    @PostMapping("update")
    public String update(@RequestParam("id") Integer id, @RequestParam("quantity") Integer quantity){
        shoppingCartService.update(id, quantity);
        return "redirect:/shopping-cart/views";
    }
}
