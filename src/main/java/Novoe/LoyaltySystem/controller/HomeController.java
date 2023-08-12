package Novoe.LoyaltySystem.controller;

import Novoe.LoyaltySystem.model.User;
import Novoe.LoyaltySystem.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {


    @Autowired
    UserService userService;


    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/")
    public String index(Model model,
                        HttpSession session){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = (User) userService.loadUserByUsername(email);
        session.setAttribute("userID", user.getId());
        session.setAttribute("userNAME", user.getName());
        return "index";
    }
    @GetMapping(value = "/login")
    public String login(){
        return "registr/login";
    }

    @GetMapping(value = "/create")
    public String createUser(){
        return "registr/create";
    }

    @PostMapping(value = "/create")
    public String createUserPost(
            Model model,
            @ModelAttribute User user
    ){
        userService.create(user);
        model.addAttribute("success", true);
        return "registr/login";
    }
}


