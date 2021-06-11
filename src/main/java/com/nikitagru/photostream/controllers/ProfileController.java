package com.nikitagru.photostream.controllers;

import com.nikitagru.photostream.entities.Subscription;
import com.nikitagru.photostream.entities.User;
import com.nikitagru.photostream.services.SubscriptionService;
import com.nikitagru.photostream.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;

@Controller
public class ProfileController {
    private UserService userService;
    private SubscriptionService subscriptionService;

    @Autowired
    public void setSubscriptionService(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/subscribe/{id}")
    public String subscribe(@PathVariable("id") Long id, Principal principal) {
        User currentUser = userService.findByUsername(principal.getName());
        subscriptionService.subscribe(currentUser.getId(), id);
        return "redirect:/userprofile/" + id;
    }

    @GetMapping("/unsubscribe/{id}")
    public String unSubscribe(@PathVariable("id") Long id, Principal principal) {
        User currentUser = userService.findByUsername(principal.getName());
        subscriptionService.unSubscribe(currentUser.getId(), id);
        return "redirect:/userprofile/" + id;
    }
}
