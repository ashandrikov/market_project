package com.shandrikov.market.market_project;

import com.shandrikov.market.market_project.shopping_cart.CartItem;
import com.shandrikov.market.market_project.item.Item;
import com.shandrikov.market.market_project.user.User;
import com.shandrikov.market.market_project.shopping_cart.CartItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class ShoppingCartTest {

    @Autowired
    private CartItemRepository cartRepo;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testAddOneCartItem(){
        Item item = entityManager.find(Item.class, 1);
        User user = entityManager.find(User.class, 9);

        CartItem newItem = new CartItem();
        newItem.setItem(item);
        newItem.setUser(user);
        newItem.setQuantity(2);

        CartItem savedCartItem = cartRepo.save(newItem);
        assertThat(savedCartItem.getId() > 0);

    }

    @Test
    public void testFindByUser(){

        User user = new User();
        user.setId(9);

        List<CartItem> listItems = cartRepo.findByUser(user);

        assertThat(listItems.size()).isEqualTo(2);
    }

    @Test
    public void testFindByUserAndItem(){
        Integer userId = 9;
        Integer itemId = 2;

        User user = new User();
        user.setId(userId);

        Item item = new Item();
        item.setId(itemId);

        CartItem cartItem = cartRepo.findByUserAndItem(user, item);
        assertThat(cartItem).isNotNull();
    }

    @Test
    public void testUpdateQuantity(){
        Integer userId = 9;
        Integer itemId = 2;
        Integer quantity = 3;

        User user = new User();
        user.setId(userId);

        Item item = new Item();
        item.setId(itemId);

        cartRepo.updateQuantity(quantity, userId, itemId);

        CartItem cartItem = cartRepo.findByUserAndItem(user, item);

        assertThat(cartItem.getQuantity()).isEqualTo(3);
    }

    @Test
    public void testDeleteByUserAndItem(){
        Integer userId = 9;
        Integer itemId = 2;

        User user = new User();
        user.setId(userId);

        Item item = new Item();
        item.setId(itemId);

        cartRepo.deleteByUserAndItem(userId,itemId);

        CartItem cartItem = cartRepo.findByUserAndItem(user, item);

        assertThat(cartItem).isNull();
    }

}
