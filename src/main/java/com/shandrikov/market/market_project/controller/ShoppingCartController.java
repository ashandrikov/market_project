package com.shandrikov.market.market_project.controller;

import com.shandrikov.market.market_project.entity.CartItem;
import com.shandrikov.market.market_project.entity.User;
import com.shandrikov.market.market_project.service.ShoppingCartException;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
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

//        authentication = SecurityContextHolder.getContext().getAuthentication();
//        User user = userService.getCurrentlyLoggedInUser(authentication);
        User user = null;
        try {
            user = getAuthenticatedUser(authentication);
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    @PostMapping("/cart/add/{itemId}/{quantity}")
    public String addItemToCart(@PathVariable("itemId") Integer itemId,
                                @PathVariable("quantity") Integer quantity,
                                HttpServletRequest request,
                                @AuthenticationPrincipal Authentication authentication){
        try {
            User user = getAuthenticatedUser(authentication);
            Integer updatedQuantity = cartService.addItem(itemId, quantity, user);
            return updatedQuantity + " товара(-ов) было добавлено в козину.";
        } catch (ShoppingCartException ex){
            return ex.getMessage();
        } catch (Exception e) {
            return "Авторизируйтесь для того чтобы добавить товар в корзину.";
        }
    }

    private User getAuthenticatedUser(@AuthenticationPrincipal Authentication authentication) throws Exception {
        authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getCurrentlyLoggedInUser(authentication);
        if (user == null){
            throw new Exception();
        }
        return user;
    }
}
