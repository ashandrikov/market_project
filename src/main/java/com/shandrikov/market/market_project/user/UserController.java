package com.shandrikov.market.market_project.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {

    @Autowired
    UserRepository userRepo;

    @GetMapping("/account")
    public String viewDetails(@AuthenticationPrincipal MyUserDetails loggedUser, Model model) {

        String username = loggedUser.getUsername();
        User user = userRepo.getUserByUsername(username);
        model.addAttribute("user", user);
        return "account_info";
    }

//    @RequestMapping("/account/update")
//    public String saveDetailsString(User user, RedirectAttributes redirectAttributes,
//            @AuthenticationPrincipal MyUserDetails loggedUser){
//
//        loggedUser.setUsername(user.getUsername());
//        loggedUser.setPassword(user.getPassword());
//
//        redirectAttributes.addFlashAttribute("message", "Данные вашего аккаунта обновлены");
//        return "redirect:/account";
//    }

}