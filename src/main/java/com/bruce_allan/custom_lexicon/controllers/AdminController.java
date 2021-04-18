package com.bruce_allan.custom_lexicon.controllers;

import com.bruce_allan.custom_lexicon.models.User;
import com.bruce_allan.custom_lexicon.repo.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    private final UserRepository userRepository;

    public AdminController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/admin")
    public String adminLogin(Model model) {
        model.addAttribute("admin", new User());
        return "lex_posts/admin";
    }
}
