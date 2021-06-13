package com.shandrikov.market.market_project.service;

import com.shandrikov.market.market_project.entity.Item;

import java.util.List;

public interface ItemService {
    public List<Item> getAllItems();

    public void saveItem(Item item);

    public Item getItem(int id);

    public void deleteItem(int id);

    public List<Item> findByName (String name);

}
