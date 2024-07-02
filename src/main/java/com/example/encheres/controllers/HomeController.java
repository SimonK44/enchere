package com.example.encheres.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

<<<<<<< Updated upstream
    @GetMapping({"/","home","encheres","listes-articles"})
    public String home(Model model) {
        model.addAttribute("message", "Bienvenue à Enchères!");
        return "home";
=======
    @GetMapping("/")
    public String home() {
//        model.addAttribute("message", "Bienvenue à Enchères!");
        return "home";        
>>>>>>> Stashed changes
    }
}


