package com.shandrikov.market.market_project.controller;

import com.shandrikov.market.market_project.entity.Category;
import com.shandrikov.market.market_project.repos.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CategoryController {
    @Autowired
    private CategoryRepository repo;

    @GetMapping("/categories")
    public String categories(Model model){
        List<Category> categories = repo.findAll();
        model.addAttribute("categories", categories);
        return "categories";
    }

    @GetMapping("/categories/new")
    public String showCategoryNewForm(Model model){
        model.addAttribute("category", new Category());
        return "category_form";
    }

    @PostMapping("/categories/save")
    public String saveCategory(Category category){
        repo.save(category);
        return "redirect:/categories";
    }

}
