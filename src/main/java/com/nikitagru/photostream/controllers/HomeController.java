package com.nikitagru.photostream.controllers;

import com.nikitagru.photostream.entities.Post;
import com.nikitagru.photostream.entities.User;
import com.nikitagru.photostream.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;

@Controller
public class HomeController {
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
            User user = userService.findByUsername(principal.getName());
            Collection<Post> subscriptionsPosts = new ArrayList<>();
            for (User subscription : user.getSubscriptions()) {
                for (Post userPosts : subscription.getPosts()) {
                    subscriptionsPosts.add(userPosts);
                }
            }

            model.addAttribute("posts", subscriptionsPosts);
            model.addAttribute("postsCount", subscriptionsPosts.size());
        }
        return "home";
    }

    @GetMapping("/userprofile")
    public String userProfile(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        Collection<Post> posts = user.getPosts();
        int subscribers = user.getSubscribers().size();
        int subscriptions = user.getSubscriptions().size();

        model.addAttribute("user", user);
        model.addAttribute("posts", user.getPosts());
        model.addAttribute("subscribers", subscribers);
        model.addAttribute("subscriptions", subscriptions);

        return "userprofile";
    }
}
