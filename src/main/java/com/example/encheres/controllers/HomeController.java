package com.example.encheres.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes({"utilisateurSession"})
public class HomeController {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @GetMapping("/test-connexion")
    public String testConnexion(Model model) {
        try {
            jdbcTemplate.queryForObject("SELECT 1", Integer.class);
            model.addAttribute("message", "Connexion à la base de données réussie");
        } catch (Exception e) {
            model.addAttribute("message", "Échec de la connexion à la base de données");
        }
        return "view-test-connexion";
    }
    @GetMapping({"/","home","encheres","listes-articles"})
    public String home(Model model) {
        model.addAttribute("message", "Bienvenue à Enchères!");
        return "home";
    }
}


