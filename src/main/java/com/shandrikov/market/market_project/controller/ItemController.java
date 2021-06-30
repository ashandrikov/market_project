package com.shandrikov.market.market_project.controller;

import com.shandrikov.market.market_project.entity.Category;
import com.shandrikov.market.market_project.entity.Item;
import com.shandrikov.market.market_project.entity.ItemInputParams;
import com.shandrikov.market.market_project.entity.User;
import com.shandrikov.market.market_project.repos.CategoryRepository;
import com.shandrikov.market.market_project.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class ItemController {

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

    @GetMapping("/orders")
    public String orders() {
        return "orders";
    }

    @GetMapping("/")
    public String main2() {
        return "main";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/items/new")
    public String showItemNewForm(Model model) {

        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        return "item_form";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/add")
    public String add() {

        return "additemcategory";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/items/edit/{id}")
    public String showEditItemForm(@PathVariable("id") Integer id, Model model) {
        Item item = itemService.getItem(id);
        model.addAttribute("item", item);

        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        return "item_form";
    }

    @GetMapping("/items/delete/{id}")
    public String deleteItem(@PathVariable("id") Integer id, Model model) {

        itemService.deleteItem(id);
        return "redirect:/items";
    }

    @GetMapping("/items")
    public String initItems(@RequestParam(required = false, defaultValue = "") String filter, Model model) {

        Iterable<Item> items = (filter == null || filter.isEmpty() ?
                itemService.getAllItems() : itemService.findByName(filter));
        model.addAttribute("items", items);
        model.addAttribute("filter", filter);

        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);

        return "items";
    }

    @PostMapping("/items/save")
    public String add(@Valid ItemInputParams formParams,
                      BindingResult bindingResult, Model model) throws IOException {


        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            model.addAttribute("item", formParams);

            List<Category> categories = categoryRepository.findAll();
            model.addAttribute("categories", categories);
            return "item_form";
        }

        Item item = itemService.getItem(formParams.getId());

        item.setName(formParams.getName());
        item.setCategory(formParams.getCategory());
        item.setDescription(formParams.getDescription());
        item.setPrice(formParams.getPrice());

        MultipartFile file = formParams.getImage();
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            byte[] data = file.getBytes();
            String imageString = Base64.getEncoder().encodeToString(data);
            item.setImage(imageString);
        }

        itemService.saveItem(item);
        Iterable<Item> items = itemService.getAllItems();

        model.addAttribute("items", items);
        return "items";
    }

}
