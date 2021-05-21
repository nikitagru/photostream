package com.nikitagru.photostream.controllers;

import com.nikitagru.photostream.entities.Post;
import com.nikitagru.photostream.entities.User;
import com.nikitagru.photostream.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.Collection;

@Controller
public class HomeController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/userprofile")
    public String userProfile(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        Collection<Post> posts = user.getPosts();
        model.addAttribute("user", user);
        model.addAttribute("posts", user.getPosts());
        return "userprofile";
    }
}
