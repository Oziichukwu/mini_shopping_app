package com.example.minishoppingoutlet.data.models;

import com.example.minishoppingoutlet.exceptions.CartException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    private BigDecimal total;

    @Transient
    private Map<String, Item>items = new HashMap<>();

    public void addItem(Product product, int quantity){
        String productId = product.getProductId();
        Item item = new Item();
        item.setProduct(product);

        if (items.containsKey(productId))
            items.get(productId).increaseQuantity(quantity);
        else items.put(productId, item);
        total = calculateTotal();
    }

    public void removeItem(String productId){
        items.remove(productId);
        total = calculateTotal();
    }

    public void removeItem(String productId, int quantity){
        if (items.containsKey(productId)){
            items.get(productId).decreaseQuantity(quantity);
        }else {
            throw new CartException("item is not int cart");
        }
        total = calculateTotal();
    }

    private BigDecimal calculateTotal() {

        BigDecimal total = BigDecimal.ZERO;
        for (Item cartItem : items.values()){
            total = total.add(cartItem.getItemTotal());
        }
        return total;
    }

}
