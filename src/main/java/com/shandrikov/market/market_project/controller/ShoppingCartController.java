package com.shandrikov.market.market_project.controller;

import com.shandrikov.market.market_project.entity.CartItem;
import com.shandrikov.market.market_project.entity.User;
import com.shandrikov.market.market_project.service.ShoppingCartService;
import com.shandrikov.market.market_project.service.UserDetailsServiceImpl;
import com.shandrikov.market.market_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.validation.constraints.Null;
import java.util.List;

@Controller
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService cartService;

    @Autowired
    private UserService userService;

    @GetMapping("/cart")
    public String showShoppingCart(Model model, @AuthenticationPrincipal Authentication authentication){

        authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getCurrentlyLoggedInUser(authentication);
        List<CartItem> cartItems = cartService.listCartItems(user);

        model.addAttribute("cartItems", cartItems);
        model.addAttribute("pageTitle", "Shopping Cart");

        return "shopping_cart";
    }
}
