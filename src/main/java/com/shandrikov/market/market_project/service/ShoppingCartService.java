package com.shandrikov.market.market_project.service;

import com.shandrikov.market.market_project.entity.CartItem;
import com.shandrikov.market.market_project.entity.Item;
import com.shandrikov.market.market_project.entity.User;
import com.shandrikov.market.market_project.repos.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartService {

    @Autowired
    private CartItemRepository cartRepo;

    public List<CartItem> listCartItems(User user){
        return cartRepo.findByUser(user);
    }

    public Integer addItem(Integer itemId, Integer quantity, User user){
        Integer updatedQuantity = quantity;
        Item item = new Item();
        item.setId(itemId);

        CartItem cartItem = cartRepo.findByUserAndItem(user, item);

        if (cartItem != null){
            updatedQuantity = cartItem.getQuantity() + quantity;
            cartItem.setQuantity(updatedQuantity);
        } else {
            cartItem = new CartItem();
            cartItem.setUser(user);
            cartItem.setItem(item);
        }
        cartItem.setQuantity(updatedQuantity);
        cartRepo.save(cartItem);

        return updatedQuantity;
    }
}
