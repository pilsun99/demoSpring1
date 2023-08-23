package niit.com.vn.demospring1.controller;

import jakarta.servlet.http.HttpSession;
import niit.com.vn.demospring1.domains.Product;
import niit.com.vn.demospring1.repository.ProductRepository;
import niit.com.vn.demospring1.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller(value = "CartController")
public class CartController {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartService cartService;

    @RequestMapping(value = "/list-product")
    public String list(Model model) {
        Iterable<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "list-product";
    }

    @RequestMapping(value = "/add-to-cart")
    public String addToCart(@RequestParam("id") Long id, HttpSession httpSession) {
        cartService.addToCart(httpSession, id);
        return "redirect:/list-product";
    }

    @RequestMapping(value = "/cart")
    public String cart(HttpSession httpSession, Model model) {
        cartService.getCartItems(httpSession);
        model.addAttribute("cartItems", cartService.getCartItems(httpSession));
        return "cart";
    }

    @RequestMapping(value = "/empty-cart")
    public String emptyCart(HttpSession httpSession) {
        cartService.emptyCart( httpSession);
        return "redirect:/cart";
    }

    @RequestMapping(value = "remove-cart-item")
    public String removeCartItem(HttpSession httpSession, Long id) {
        cartService.removeCartItem(id , httpSession);
        return "redirect:/cart";
    }

    @RequestMapping(value = "/update")
    public String updateQuantity(@RequestParam("id") Long id, @RequestParam("quantity") Integer quantity, HttpSession httpSession) {
        cartService.updateQuantity(id, quantity, httpSession);
        return "redirect:/cart";
    }
}
