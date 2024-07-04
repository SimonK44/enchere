package com.example.encheres.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.encheres.bll.ArticleVenduService;
import com.example.encheres.bll.CategorieService;
import com.example.encheres.bo.ArticleVendu;
import com.example.encheres.bo.Categorie;
import com.example.encheres.bo.Utilisateur;

@Controller
@SessionAttributes({"utilisateurSession"})
public class HomeController {
	private ArticleVenduService articleVenduService;
	private CategorieService categorieService;
	private List <ArticleVendu> articles;
	
	public HomeController(ArticleVenduService articleVenduService, CategorieService categorieService,
			List<ArticleVendu> articles) {
		this.articleVenduService = articleVenduService;
		this.categorieService = categorieService;
		this.articles = articles;
	}

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
    	List <ArticleVendu> articles = new ArrayList<ArticleVendu>();
    	articles.add(new ArticleVendu(1, "Une pipe","ceci n est pas une pipe",LocalDate.now(),LocalDate.now(), 10, 110, new Categorie(1, "informatique"),new Utilisateur(),new ArrayList<enchere>()));
    	articles.add(new ArticleVendu(2, "Table de salon" ,"une tres belle table en chene", "2024-07-01","2024-07-30", 100, 150, 1, 2, 1));
    	model.addAttribute("articles",articles);
		model.addAttribute("categories", categorieService.findAll());
        return "home";
    }
}


