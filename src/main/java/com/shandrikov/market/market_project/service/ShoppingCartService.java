package com.shandrikov.market.market_project.service;

import com.shandrikov.market.market_project.entity.CartItem;
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
}
