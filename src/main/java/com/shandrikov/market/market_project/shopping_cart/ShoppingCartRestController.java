package com.shandrikov.market.market_project.shopping_cart;

import com.shandrikov.market.market_project.user.User;
import com.shandrikov.market.market_project.shopping_cart.ShoppingCartException;
import com.shandrikov.market.market_project.shopping_cart.ShoppingCartService;
import com.shandrikov.market.market_project.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ShoppingCartRestController {

    @Autowired
    private ShoppingCartService cartService;

    @Autowired
    private UserService userService;

    @PostMapping("/cart/add/{itemId}/{quantity}")
    public String addItemToCart(@PathVariable("itemId") Integer itemId,
                                @PathVariable("quantity") Integer quantity,
                                HttpServletRequest request,
                                @AuthenticationPrincipal Authentication authentication){


        try {
            authentication = SecurityContextHolder.getContext().getAuthentication();
            User user = userService.getCurrentlyLoggedInUser(authentication);

//            User user = getAuthenticatedUser(authentication);
            Integer updatedQuantity = cartService.addItem(itemId, quantity, user);
            return updatedQuantity + " товара(-ов) было добавлено в козину.";
        } catch (ShoppingCartException ex){
            return ex.getMessage();
        } catch (Exception e) {
            return "Авторизируйтесь для того чтобы добавить товар в корзину.";
        }
    }

//    private User getAuthenticatedUser(@AuthenticationPrincipal Authentication authentication) throws Exception {
//        authentication = SecurityContextHolder.getContext().getAuthentication();
//        User user = userService.getCurrentlyLoggedInUser(authentication);
//        if (user == null){
//            throw new Exception();
//        }
//        return user;
//    }
}
