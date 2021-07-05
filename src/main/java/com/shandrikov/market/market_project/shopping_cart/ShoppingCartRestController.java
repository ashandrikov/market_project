package com.shandrikov.market.market_project.shopping_cart;

import com.shandrikov.market.market_project.controller.ControllerUtils;
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

//    @PostMapping("/cart/add/{itemId}/{quantity}")
//    public String addItemToCart(@PathVariable("itemId") Integer itemId,
//                                @PathVariable("quantity") Integer quantity,
//                                HttpServletRequest request,
//                                @AuthenticationPrincipal Authentication authentication){
//
//
//        try {
//            authentication = SecurityContextHolder.getContext().getAuthentication();
//            User user = userService.getCurrentlyLoggedInUser(authentication);
//
////            User user = getAuthenticatedUser(authentication);
//            Integer updatedQuantity = cartService.addItem(itemId, quantity, user);
//            return updatedQuantity + " товара(-ов) было добавлено в козину.";
//        } catch (ShoppingCartException ex){
//            return ex.getMessage();
//        } catch (Exception e) {
//            return "Авторизируйтесь для того чтобы добавить товар в корзину.";
//        }
//    }

    @PostMapping("/cart/add/{itemId}/{quantity}")
    public String addItemToCart(@PathVariable("itemId") Integer itemId,
                                @PathVariable("quantity") Integer quantity,
                                HttpServletRequest request) throws Exception {

        try {User user = getAuthenticatedUser(request);
        Integer updatedQuantity = cartService.addItem(itemId, quantity, user);
            return updatedQuantity + " товара(-ов) было добавлено в козину.";
        }catch (Exception e) {
            return "Авторизируйтесь для того чтобы добавить товар в корзину.";
        }

    }

//    @PostMapping("/cart/update/{itemId}/{quantity}")
//    public String updateQuantity(@PathVariable("itemId") Integer itemId,
//                                @PathVariable("quantity") Integer quantity,
//                                HttpServletRequest request,
//                                @AuthenticationPrincipal Authentication authentication){
//        try {
//            authentication = SecurityContextHolder.getContext().getAuthentication();
//            User user = userService.getCurrentlyLoggedInUser(authentication);
//            int subtotal = cartService.updateQuantity(itemId, quantity, user);
////            User user = getAuthenticatedUser(authentication);
//            return String.valueOf(subtotal);
//        } catch (Exception ex){
//            return ex.getMessage();
//        }
//
//    }

    @PostMapping("/cart/update/{itemId}/{quantity}")
    public String updateQuantity(@PathVariable("itemId") Integer itemId,
                                 @PathVariable("quantity") Integer quantity,
                                 HttpServletRequest request){
        try {
            User user = getAuthenticatedUser(request);
            int subtotal = cartService.updateQuantity(itemId, quantity, user);
            return String.valueOf(subtotal);
        } catch (Exception ex){
            return ex.getMessage();
        }

    }

    private User getAuthenticatedUser(HttpServletRequest request) throws Exception {
        String username = ControllerUtils.getUsernameOfAuthenticatedUser(request);
        if (username == null) {
            throw new Exception("No authenticated customer");
        }
        return userService.getUserByUsername(username);
    }
}
