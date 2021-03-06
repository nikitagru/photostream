package com.nikitagru.photostream.controllers;

import com.nikitagru.photostream.entities.Post;
import com.nikitagru.photostream.entities.User;
import com.nikitagru.photostream.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/***
 * Home page controller
 */
@Controller
public class HomeController {
    private UserService userService;

    @Value("${upload.path}")
    private String imagePath;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /***
     * Generate home page
     * @param principal current user
     * @param model MVC model
     * @return home page
     */
    @GetMapping("/")
    public String homePage(Principal principal, Model model) {
        if (principal == null) {
            model.addAttribute("name", "non-auth-user");
        } else {
            User user = userService.findByUsername(principal.getName());
            HashMap<User, List<Post>> subscriptionsPosts = new HashMap<>();
            for (User subscription : user.getSubscriptions()) {
                List<Post> userPosts = new ArrayList<>();
                for (Post userPost : subscription.getPosts()) {
                    userPosts.add(userPost);
                }
                subscriptionsPosts.put(subscription, userPosts);
            }

            model.addAttribute("posts", subscriptionsPosts);
            model.addAttribute("postsCount", subscriptionsPosts.size());
            model.addAttribute("imagePath", imagePath);
        }
        return "home";
    }

    /***
     * Generate current user profile page
     * @param model MVC model
     * @param principal current user
     * @return current user's page
     */
    @GetMapping("/userprofile")
    public String userProfile(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());

        initializeUser(model, user);
        model.addAttribute("isSubscribe", null);


        return "userprofile";
    }

    /***
     * Initialize basic variables for profile page
     * @param model MVC model
     * @param user user, which page will be generate
     */
    private void initializeUser(Model model, User user) {
        int subscribers = user.getSubscribers().size();
        int subscriptions = user.getSubscriptions().size();

        model.addAttribute("user", user);
        model.addAttribute("posts", user.getPosts());
        model.addAttribute("subscribers", subscribers);
        model.addAttribute("subscriptions", subscriptions);
        model.addAttribute("imagePath", imagePath);
    }

    /***
     * Generate all but current user's page
     * @param id user id
     * @param model MVC model
     * @param principal current user
     * @return
     */
    @GetMapping("/userprofile/{id}")
    public String currentUserprofile(@PathVariable("id") Long id, Model model, Principal principal) {
        User userToShow = userService.findById(id);

        if (userToShow != null) {
            if (userToShow.getUsername().equals(principal.getName())) {
                return "redirect:/userprofile";
            }
            initializeUser(model, userToShow);
            User currentUser = userService.findByUsername(principal.getName());

            if (currentUser.getSubscriptions().contains(userToShow)) {
                model.addAttribute("isSubscribe", true);
            } else {
                model.addAttribute("isSubscribe", false);
            }

            return "userprofile";
        }


        return "home";
    }
}
