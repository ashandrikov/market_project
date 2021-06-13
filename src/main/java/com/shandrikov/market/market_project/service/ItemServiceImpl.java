package com.shandrikov.market.market_project.service;

import com.shandrikov.market.market_project.repos.ItemRepository;
import com.shandrikov.market.market_project.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    @Override
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    @Override
    public Item getItem(int id) {
        Item item = null;
        Optional<Item>optional = itemRepository.findById(id);
        if (optional.isPresent()){
            item = optional.get();
        }
        return item;
    }

    @Override
    public void deleteItem(int id) {
        itemRepository.deleteById(id);
    }

    @Override
    public List<Item> findByName(String name) {
        return itemRepository.findAllByNameContains(name);
    }
}
