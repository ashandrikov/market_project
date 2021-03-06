package com.shandrikov.market.market_project.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public String showCategoryNewForm(){
        return "category_form";
    }

    @PostMapping("/categories/save")
    public String saveCategory(Category category){
        repo.save(category);
        return "redirect:/categories";
    }

    @GetMapping("/categories/edit/{id}")
    public String showEditItemForm(@PathVariable("id") Integer id, Model model) {
        Category category = repo.getById(id);
        model.addAttribute("category", category);

        return "category_form";
    }

}
