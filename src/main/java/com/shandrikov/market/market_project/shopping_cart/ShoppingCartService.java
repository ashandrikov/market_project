package com.shandrikov.market.market_project.shopping_cart;

import com.shandrikov.market.market_project.item.Item;
import com.shandrikov.market.market_project.item.ItemRepository;
import com.shandrikov.market.market_project.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ShoppingCartService {

    @Autowired
    private CartItemRepository cartRepo;

    @Autowired
    private ItemRepository itemRepo;

    public List<CartItem> listCartItems(User user){
        return cartRepo.findByUser(user);
    }

    public Integer addItem(Integer itemId, Integer quantity, User user) throws ShoppingCartException {
        Integer updatedQuantity = quantity;
        Item item = new Item();
        item.setId(itemId);

        CartItem cartItem = cartRepo.findByUserAndItem(user, item);

        if (cartItem != null){
            updatedQuantity = cartItem.getQuantity() + quantity;
            if (updatedQuantity > 5){
                throw new ShoppingCartException("Невозможно добавить больше" + quantity + " товаров потому что" +
                        "в корзине уже " + cartItem.getQuantity() + " штук. Максимум 5.");
            }
        } else {
            cartItem = new CartItem();
            cartItem.setUser(user);
            cartItem.setItem(item);
        }
        cartItem.setQuantity(updatedQuantity);
        cartRepo.save(cartItem);

        return updatedQuantity;
    }

    public int updateQuantity(Integer itemId, Integer quantity, User user){
        cartRepo.updateQuantity(quantity, user.getId(), itemId);
        Item item = itemRepo.findById(itemId).get();
        int subTotal = item.getPrice() * quantity;
        return subTotal;
    }
}
