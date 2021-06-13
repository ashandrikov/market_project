package com.shandrikov.market.market_project.repos;

import com.shandrikov.market.market_project.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Integer> {
    public List<Item> findAllByNameContains(String name);
}
