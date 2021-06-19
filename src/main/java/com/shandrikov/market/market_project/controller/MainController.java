package com.shandrikov.market.market_project.controller;

import com.shandrikov.market.market_project.entity.Category;
import com.shandrikov.market.market_project.entity.Item;
import com.shandrikov.market.market_project.entity.User;
import com.shandrikov.market.market_project.repos.CategoryRepository;
import com.shandrikov.market.market_project.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
public class MainController {

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private ItemService itemService;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/main")
    public String main() {
        return "main";
    }

    @GetMapping("/cart")
    public String cart() {
        return "cart";
    }

    @GetMapping("/profile")
    public String profile() {
        return "profile";
    }

    @GetMapping("/orders")
    public String orders() {
        return "orders";
    }


    @GetMapping("/")
    public String main2() {
        return "main";
    }

    @GetMapping("/items")
    public String main(
            @RequestParam(required = false, defaultValue = "") String filter,
            Model model) {
            Iterable<Item> items;

        if (filter != null && !filter.isEmpty()) {
            items = itemService.findByName(filter);
        } else {
            items = itemService.getAllItems();
        }

        model.addAttribute("items", items);
        model.addAttribute("filter", filter);

        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);

        return "items";
    }

    @PostMapping("/items")
    public String add(
            @AuthenticationPrincipal User user,
//            @Valid Item item,

            @RequestParam String name,
            @RequestParam Category category_id,
            @RequestParam String description,
            @RequestParam int price,
            @RequestParam("image") MultipartFile file,

//            BindingResult bindingResult,
            Model model

    ) throws IOException {

        Item item = new Item();
        item.setName(name);
        item.setCategory_id(category_id);
        item.setDescription(description);
        item.setPrice(price);
        item.setAuthor(user);

        if (file != null && !file.getOriginalFilename().isEmpty()) {
            byte[] data = file.getBytes();
            String imageString = Base64.getEncoder().encodeToString(data);
            item.setImage(imageString);
        }

//        if (bindingResult.hasErrors()){
//            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
//            model.mergeAttributes(errorsMap);
//            model.addAttribute("item", item);
//        } else {
//            model.addAttribute("item", null);
//            itemService.saveItem(item);
//        }

        itemService.saveItem(item);

        Iterable<Item> items = itemService.getAllItems();
        model.addAttribute("items", items);
        return "main";
    }

}
