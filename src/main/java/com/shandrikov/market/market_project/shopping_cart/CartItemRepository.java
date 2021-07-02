package com.shandrikov.market.market_project.shopping_cart;

import com.shandrikov.market.market_project.item.Item;
import com.shandrikov.market.market_project.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {

    public List<CartItem> findByUser(User user);

    public CartItem findByUserAndItem(User user, Item item);

    @Modifying
    @Query("UPDATE CartItem c SET c.quantity =?1 WHERE c.user.id =?2 AND c.item.id =?3")
    public void updateQuantity(Integer quantity, Integer userId, Integer itemId);

    @Modifying
    @Query("DELETE FROM CartItem c WHERE c.user.id =?1 AND c.item.id =?2")
    public void deleteByUserAndItem(Integer userId, Integer itemId);
}
