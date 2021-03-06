package com.shandrikov.market.market_project.item;

import com.shandrikov.market.market_project.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
    public List<Item> findAllByNameContains(String name);
}
