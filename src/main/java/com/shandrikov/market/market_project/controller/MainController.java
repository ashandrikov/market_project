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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
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
    public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
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
//            @RequestParam("file") MultipartFile file,
            @RequestParam("image") MultipartFile file2,
            Map<String, Object> model

    ) throws IOException {

        Item item = new Item();
        item.setCategory_id(category_id);
        item.setName(name);
        item.setDescription(description);
        item.setPrice(price);

        if (file2 != null && !file2.getOriginalFilename().isEmpty()) {

//            File uploadDir = new File(uploadPath);
//            String uuidFile = UUID.randomUUID().toString();
//            String resultFilename = uuidFile + "." + file2.getOriginalFilename();
//            file2.transferTo(new File(uploadPath + "/" + resultFilename));

            //Попытка упростить, но где-то ошибка

            byte[] bytes = file2.getBytes();
            Path path = Paths.get(uploadPath + "/" + file2.getOriginalFilename());
            Files.write(path, bytes);

//            File file = new File(resultFilename);
//            byte[] picInBytes = new byte[(int) file.length()];
//            FileInputStream fileInputStream = new FileInputStream(file);
//            fileInputStream.read(picInBytes);
//            fileInputStream.close();
            item.setImage(bytes);
        }

//        Прежний сложный но работающий вариант

//        Item item = new Item(category, name, description, price, file, user);
//        if (file != null && !file.getOriginalFilename().isEmpty()){
//            File uploadDir = new File(uploadPath);
//            if (!uploadDir.exists()) {
//                uploadDir.mkdir();
//            }
//            String uuidFile = UUID.randomUUID().toString();
//            String resultFilename = uuidFile + "." + file.getOriginalFilename();
//            file.transferTo(new File(uploadPath + "/" + resultFilename));
//            item.setFilename(resultFilename);
//        }

        itemService.saveItem(item);

        Iterable<Item> items = itemService.getAllItems();

        model.put("items", items);

        return "items";
    }

}
