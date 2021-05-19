package com.nikitagru.photostream.controllers;

import com.nikitagru.photostream.entities.User;
import com.nikitagru.photostream.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class MainController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String homePage(Principal principal, Model model) {
        if (principal == null) {
            model.addAttribute("name", "non-auth-user");
        } else {
            model.addAttribute("name", principal.getName());
        }
        return "home";
    }

    @GetMapping("/authenticated")
    public String pageForAuthenticatedUsers(Principal principal, Model model) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("name", user.getUsername());
        model.addAttribute("mail", user.getEmail());
        return "authenticated";
    }

    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

    @GetMapping("/login")
    public String signInPage() {
        return "login";
    }

    @GetMapping("/logout")
    public String logoutPage() {
        return "logout";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String registrationNewUser(@ModelAttribute User user) {
        User existUser = userService.findByUsername(user.getUsername());
        if (existUser != null) {
            return "registration";
        }
        userService.registerNewUser(user);
        return "login";
    }
}
