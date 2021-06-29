package com.shandrikov.market.market_project.repos;

import com.shandrikov.market.market_project.entity.CartItem;
import com.shandrikov.market.market_project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {

    public List<CartItem> findByUser(User user);
}
