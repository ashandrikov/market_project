package com.shandrikov.market.market_project.controller;

import com.shandrikov.market.market_project.entity.MyUserDetails;
import com.shandrikov.market.market_project.entity.User;
import com.shandrikov.market.market_project.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AccountController {

    @Autowired
    UserRepository userRepo;

    @GetMapping("/account")
    public String viewDetails(@AuthenticationPrincipal MyUserDetails loggedUser, Model model) {

        String username = loggedUser.getUsername();
        User user = userRepo.getUserByUsername(username);
        model.addAttribute("user", user);
        return "account_form";
    }

    @PostMapping("/account/update")
    public String saveDetailsString(User user, RedirectAttributes redirectAttributes,
            @AuthenticationPrincipal MyUserDetails loggedUser){

        loggedUser.setUsername(user.getUsername());
        loggedUser.setPassword(user.getPassword());

        redirectAttributes.addFlashAttribute("message", "Данные вашего аккаунта обновлены");
        return "redirect:/account_form";
    }

}