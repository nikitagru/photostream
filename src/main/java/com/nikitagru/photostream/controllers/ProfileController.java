package com.nikitagru.photostream.controllers;

import com.nikitagru.photostream.entities.Post;
import com.nikitagru.photostream.entities.Subscription;
import com.nikitagru.photostream.entities.User;
import com.nikitagru.photostream.services.PostService;
import com.nikitagru.photostream.services.SubscriptionService;
import com.nikitagru.photostream.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.awt.datatransfer.FlavorEvent;
import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.UUID;

@Controller
public class ProfileController {
    private UserService userService;
    private SubscriptionService subscriptionService;
    private PostService postService;

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    public void setSubscriptionService(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @Autowired
    public void setPostService(PostService postService) {
        this.postService = postService;
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

    @PostMapping("/upload")
    public String uploadPost(@RequestParam("photo") MultipartFile file,
                                   @RequestParam("postText") String postText,
                                   Principal principal) {
        if (file != null) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFileName = UUID.randomUUID().toString();
            String resultFileName = uuidFileName + "_" + file.getOriginalFilename();

            try {
                file.transferTo(new File(uploadPath + resultFileName));
            } catch (IOException e) {
                e.printStackTrace();
            }

            String cutImageName = resultFileName.split("\\.")[0];

            Post post = new Post();
            post.setImagename(resultFileName);
            post.setCutImageName(cutImageName);
            post.setText(postText);

            User user = userService.findByUsername(principal.getName());

            postService.savePost(post, user.getId());
        }

        return "redirect:/userprofile";
    }

    @PostMapping("/changeavatar")
    public String changeAvatar(@RequestParam("photo") MultipartFile file, Principal principal) {
        User currentUser = userService.findByUsername(principal.getName());

        if (file != null) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFileName = UUID.randomUUID().toString();
            String resultFileName = uuidFileName + "_" + file.getOriginalFilename();

            try {
                file.transferTo(new File(uploadPath + resultFileName));
            } catch (IOException e) {
                e.printStackTrace();
            }

            currentUser.setProfileImageName(resultFileName);
        }

        userService.updateUser(currentUser);

        return "redirect:/userprofile";
    }
}
