package com.example.encheres.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.encheres.bll.ArticleVenduService;
import com.example.encheres.bll.CategorieService;
import com.example.encheres.bll.EnchereService;
import com.example.encheres.bll.UtilisateurService;
import com.example.encheres.bo.ArticleVendu;
import com.example.encheres.bo.Categorie;
import com.example.encheres.bo.Enchere;
import com.example.encheres.bo.Utilisateur;

@Controller
@SessionAttributes({"utilisateurSession"})
public class HomeController {
	private ArticleVenduService articleVenduService;
	private UtilisateurService utilisateurService;


	public HomeController(ArticleVenduService articleVenduService) {
		this.articleVenduService = articleVenduService;
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
    public String home(
    	Model model) {
    	List<ArticleVendu> articles = this.articleVenduService.findAll();
    	System.out.println(articles);
    	
    	
    	
    	model.addAttribute("articles", articles);
    	
    	
    	
    	
    	//ArticleVendu [noArticle=1, nomArticle=Table de salon, description=une tres belle table en chene, 
    	//dateDebutEnchere=2024-07-01, dateFinEnchere=2024-07-30, prixInitial=100.0, prixVente=150.0, 
    	//categorie=Categorie [noCategorie=1, libelle=null], acheteur=Utilisateur [noUtilisateur=2, pseudo=null, nom=null, prenom=null, email=null, telephone=null, rue=null, codePostal=null, ville=null, motDePasse=null, credit=0, administrateur=false], 
    	//vendeur=Utilisateur [noUtilisateur=1, pseudo=null, nom=null, prenom=null, email=null, telephone=null, rue=null, codePostal=null, ville=null, motDePasse=null, credit=0, administrateur=false], encheres=[]], 
    	
    	return "home";
    	
    	
    	
    	
    }
}


