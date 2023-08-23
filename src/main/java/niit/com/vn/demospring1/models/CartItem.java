package niit.com.vn.demospring1.models;

import lombok.Data;
import niit.com.vn.demospring1.domains.Product;

import java.io.Serializable;

@Data
public class CartItem implements Serializable {

    private Product product;
    private int quantity = 0;

    public double getAmount(){
        return product.getPrice()* quantity;
    }

}
