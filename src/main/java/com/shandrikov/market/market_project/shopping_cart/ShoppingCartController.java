package com.shandrikov.market.market_project.shopping_cart;

import com.shandrikov.market.market_project.shopping_cart.CartItem;
import com.shandrikov.market.market_project.user.User;
import com.shandrikov.market.market_project.shopping_cart.ShoppingCartService;
import com.shandrikov.market.market_project.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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

        int estimatedTotal = 0;
        for (CartItem item : cartItems) {
            estimatedTotal += item.getSubtotal();
        }

        model.addAttribute("cartItems", cartItems);
        model.addAttribute("pageTitle", "Shopping Cart");
        model.addAttribute("estimatedTotal", estimatedTotal);

        return "shopping_cart";
    }

}
