package com.shandrikov.market.market_project.controller;

import com.shandrikov.market.market_project.entity.Category;
import com.shandrikov.market.market_project.entity.Item;
import com.shandrikov.market.market_project.entity.User;
import com.shandrikov.market.market_project.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/")
public class MainController {

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private ItemService itemService;

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
            HttpServletResponse response,
            Model model) throws IOException {
        Iterable<Item> items;

        if (filter != null && !filter.isEmpty()) {
            items = itemService.findByName(filter);
        } else {
            items = itemService.getAllItems();
        }

        model.addAttribute("items", items);
        model.addAttribute("filter", filter);

        return "items";
    }

    @PostMapping("/items")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam int category_id,
//            @RequestParam Category category,
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam int price,
            @RequestParam("image") MultipartFile file,
            RedirectAttributes ra,
            Map<String, Object> model

    ) throws IOException {

        Item item = new Item();
        item.setCategory_id(category_id);
        item.setName(name);
        item.setDescription(description);
        item.setPrice(price);
        item.setAuthor(user);

        if (file != null && !file.getOriginalFilename().isEmpty()) {
            item.setImage(file.getBytes());
        }

        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadPath + "/" + resultFilename));

        }

        itemService.saveItem(item);
        ra.addFlashAttribute("message", "The file has been uploaded successfully!");

        Iterable<Item> items = itemService.getAllItems();

        model.put("items", items);

        return "items";
    }

}
