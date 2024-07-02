package com.example.encheres.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping({"/","home","encheres","listes-articles"})
    public String home(Model model) {
        model.addAttribute("message", "Bienvenue à Enchères!");
        return "home";
    }
}
