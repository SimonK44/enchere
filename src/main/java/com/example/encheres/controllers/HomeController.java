package com.example.encheres.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.encheres.bll.ArticleVenduService;
import com.example.encheres.bll.CategorieService;
import com.example.encheres.bll.UtilisateurService;
import com.example.encheres.bo.ArticleVendu;
import com.example.encheres.bo.Categorie;
import com.example.encheres.bo.Utilisateur;
import com.example.encheres.exception.BusinessException;

@Controller
@SessionAttributes({"utilisateurSession"})
public class HomeController {
	private ArticleVenduService articleVenduService;
	private UtilisateurService utilisateurService;
	private CategorieService categorieService;


	public HomeController(ArticleVenduService articleVenduService, UtilisateurService utilisateurService,
			CategorieService categorieService) {
		this.articleVenduService = articleVenduService;
		this.utilisateurService = utilisateurService;
		this.categorieService = categorieService;
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

    @GetMapping({"/","/home","/encheres","/listes-articles"})
    public String home(
    	Model model ) {
    	List<ArticleVendu> articles = this.articleVenduService.findAll();

        List<Categorie> categories = this.categorieService.findAll();
        
    	model.addAttribute("articles", articles);
    	model.addAttribute("categories", categories);

  	return "home";
    }

   //à developer


   @PostMapping({"/","home","encheres","listes-articles"})

	public String homeRecherche(
    		@RequestParam("filtre")String nomArticle,
    		@RequestParam("categorie") int noCategorie,
    		@RequestParam(name="transactionType", required = false, defaultValue = " ")String transactionType,
    		@RequestParam(name="encheresOuvertes", required = false, defaultValue = "0") int encheresOuvertes,
    		@RequestParam(name="encheresEnCours", required = false, defaultValue="0") int encheresEnCours,
    		@RequestParam(name="encheresRemportees",required = false, defaultValue="0") int encheresRemportees,
    		@RequestParam(name="venteCours", required = false, defaultValue="0") int venteCours,
    		@RequestParam(name="venteNonDebute", required = false, defaultValue="0") int venteNonDebute,
    		@RequestParam(name="venteTerminees",required = false,defaultValue="0") int venteTerminees,
    		@ModelAttribute("utilisateurSession") Utilisateur utilisateurSession,
    		Model model,
    		BindingResult bingingResult
   ) {
	   System.out.println("debut home post mapping");
		int requete = 0;
		if ( transactionType.equals("achat") ) {
			requete = encheresOuvertes + encheresEnCours + encheresRemportees;
		} else {
			requete = venteCours + venteNonDebute + venteTerminees;
		}
		List<ArticleVendu> articles;
		try {
			System.out.println("utilisateurSession : " + utilisateurSession);   
			if (utilisateurSession.getNoUtilisateur() != 0) {
			articles = this.articleVenduService.findAllComplexe(transactionType, requete, nomArticle, noCategorie,
			utilisateurSession.getNoUtilisateur(), utilisateurSession.getNoUtilisateur());
			} else {
			System.out.println("lil nomArticle " + nomArticle);
			System.out.println("lil noCategorie " + noCategorie);
			articles = this.articleVenduService.findFilter(nomArticle, noCategorie);
			}
			List<Categorie> categories = this.categorieService.findAll();


			  model.addAttribute("articles", articles);
			    model.addAttribute("categories", categories);

			  return "home";
		} catch (BusinessException e) {
			e.getErreurs().forEach(err -> {
				ObjectError error = new ObjectError("globalError", err);
				bingingResult.addError(error);
			});
			 return "home";
		}
	}
}
