package niit.com.vn.demospring1.services;

import jakarta.servlet.http.HttpSession;
import niit.com.vn.demospring1.domains.Product;
import niit.com.vn.demospring1.models.CartItem;
import niit.com.vn.demospring1.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CartService {
    private  static final String CART = "cart";
    @Autowired
    ProductRepository productRepository;

    public ArrayList<CartItem> getCartItems(HttpSession httpSession) {
        if (httpSession.getAttribute(CART) == null) {
            return null;
        }
        return (ArrayList<CartItem>) httpSession.getAttribute(CART);
    }

    public void addToCart(HttpSession httpSession, Long id) {
        Product product = productRepository.findById(id).get();
        // có 2 trường hợp
        // Case 1 : Chưa có giỏ hàng
        // Case 2 : Đã có giỏ hàng
        if (httpSession.getAttribute(CART) == null) {
            // Case 1: chưa giỏ hàng
            ArrayList<CartItem> cartItems = new ArrayList<>();
            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setQuantity(1);
            cartItems.add(cartItem);
            httpSession.setAttribute(CART, cartItems);
        } else {
            // Case 2 : đã có giỏ hàng
            // -> lấy ngược sản phẩm ra
            ArrayList<CartItem> cartItems = (ArrayList<CartItem>) httpSession.getAttribute(CART);
            boolean isExist = false; // biến cờ để check xem trong giỏ hàng đã sản phẩm chưa;
            for (CartItem cartItem : cartItems) {
                if (cartItem.getProduct().getId().equals(product.getId())) {
                    //Có sản phẩm trong giỏ hàng
                    cartItem.setQuantity(cartItem.getQuantity() + 1);// mang giỏ hàng tăng +1
                    isExist = true;
                    break;
                }
            }
            if (!isExist) { // chưa có sản phẩm trong giỏ hàng
                CartItem cartItem = new CartItem();
                cartItem.setProduct(product);
                cartItem.setQuantity(1);
                cartItems.add(cartItem);
            }
            httpSession.setAttribute(CART, getCartItems(httpSession));
        }
    }

    public void emptyCart(HttpSession httpSession) {
        httpSession.removeAttribute(CART);
    }

    public void removeCartItem(Long id, HttpSession httpSession) {
        ArrayList<CartItem> cartItems = getCartItems(httpSession);
        for (CartItem cartItem : cartItems) {
            if (cartItem.getProduct().getId().equals(id)) {
                cartItems.remove(cartItem);
                break;
            }
        }
        httpSession.setAttribute(CART, cartItems);
    }

    public void updateQuantity(Long id, int quantity, HttpSession httpSession) {
        ArrayList<CartItem> cartItems = getCartItems(httpSession);
        for (CartItem cartItem : cartItems) {
            if (cartItem.getProduct().getId().equals(id)) {
                if (cartItem.getQuantity() + quantity == 0) {
                    cartItems.remove(cartItem);
                    break;
                }
                cartItem.setQuantity(cartItem.getQuantity() + quantity);
                break;
            }
        }
        httpSession.setAttribute(CART, cartItems);
    }
}