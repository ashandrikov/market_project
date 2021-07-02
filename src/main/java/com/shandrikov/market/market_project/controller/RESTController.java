package com.shandrikov.market.market_project.controller;

import com.shandrikov.market.market_project.item.Item;
import com.shandrikov.market.market_project.item.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RESTController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/items")
    public List<Item> showAllEmployees() {
        List<Item> allEmployees = itemService.getAllItems();
        return allEmployees;
    }

    @GetMapping("/items/{id}")
    public Item getEmployee(@PathVariable int id) {
        Item item = itemService.getItem(id);

        return item;
    }

    @PostMapping("/items")
    public Item addNewEmployee(@RequestBody Item item) {
        itemService.saveItem(item);
        return item;
    }

    @PutMapping("/items")
    public Item updateEmployee(@RequestBody Item item) {

        itemService.saveItem(item);
        return item;
    }

    @DeleteMapping("/items/{id}")
    public String deleteEmployee(@PathVariable int id) {

        Item item = itemService.getItem(id);

        itemService.deleteItem(id);
        return "Item with id = " + id + " was deleted";

    }
}
