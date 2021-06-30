package com.shandrikov.market.market_project;

import com.shandrikov.market.market_project.entity.CartItem;
import com.shandrikov.market.market_project.entity.Item;
import com.shandrikov.market.market_project.entity.User;
import com.shandrikov.market.market_project.repos.CartItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PreferencesPlaceholderConfigurer;
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
        Item item = entityManager.find(Item.class, 3);
        User user = entityManager.find(User.class, 5);

        CartItem newItem = new CartItem();
        newItem.setItem(item);
        newItem.setUser(user);
        newItem.setQuantity(1);

        CartItem savedCartItem = cartRepo.save(newItem);
        assertThat(savedCartItem.getId() > 0);

    }

    @Test
    public void testGetCartItemsByCustomer(){
        User user = new User();
        user.setId(5);

        List<CartItem> cartItems = cartRepo.findByUser(user);
        assertEquals(1, cartItems.size());

    }

}
