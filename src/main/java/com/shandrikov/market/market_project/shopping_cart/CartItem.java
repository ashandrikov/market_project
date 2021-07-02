package com.shandrikov.market.market_project.shopping_cart;

import com.shandrikov.market.market_project.item.Item;
import com.shandrikov.market.market_project.user.User;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "cart_items")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private int quantity;

    @Transient
    public Integer getSubtotal(){
        return item.getPrice() * quantity;
    }
}
