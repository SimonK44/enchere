package com.example.encheres.controllers;

import com.example.encheres.bll.ArticleVenduService;
import com.example.encheres.bll.CategorieService;
import com.example.encheres.bll.RetraitService;
import com.example.encheres.bll.UtilisateurService;
import com.example.encheres.bo.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes({"utilisateurSession"})
public class ArticleVenduController {
	private ArticleVenduService articleVenduService;
	private CategorieService categorieService;
	private UtilisateurService utilisateurService;
	private RetraitService retraitService;
	@Autowired
	public ArticleVenduController(
			ArticleVenduService articleVenduService,
			CategorieService categorieService,
			UtilisateurService utilisateurService,
			RetraitService retraitService
	) {
		this.articleVenduService = articleVenduService;
		this.categorieService = categorieService;
		this.utilisateurService = utilisateurService;
		this.retraitService = retraitService;
	}

	@GetMapping("/vendre-article")
	public String vendreArticle(Model model) {
		ArticleVendu articleVendu = new ArticleVendu();
		articleVendu.setNomArticle("LocalDate.now()");
		articleVendu.setDateDebutEnchere(LocalDate.now());
		articleVendu.setDateFinEnchere(LocalDate.now().plusMonths(1));
		System.out.println(articleVendu);
		model.addAttribute("article", articleVendu);
		model.addAttribute("categories", categorieService.findAll());
		model.addAttribute("adresse", new Retrait());
		return "view-encher-creation";
	}

	@PostMapping("/creer")
	public String creerArticle(
		@ModelAttribute("vendre-article") ArticleVendu articleVendu,
		@ModelAttribute("adresse") Retrait adresse,
		@ModelAttribute("utilisateurSession") Utilisateur user
	) {
		this.articleVenduService.createArticleWithRetrait(articleVendu, adresse, user);
		return "redirect:view-encher-detail?id=" + articleVendu.getNoArticle();
	}



	@GetMapping("/view-resultat-gagnant")
	public String resultatRetraitGagnant() {
		return "view-resultat-gagnant";
	}

	@GetMapping("/view-resultat-retrait")
	public String resultatRetrait() {
		return "view-resultat-retrait";
	}


	@GetMapping("/view-encher-detail")
	public String pageArticleDetail(@RequestParam(value = "id", required = false) int noArticleVendu, Model model) {
		System.out.println("\n \n \n " + this.articleVenduService.lectureArticleVendu(noArticleVendu));
		ArticleVendu article = this.articleVenduService.lectureArticleVendu(noArticleVendu);

		Categorie categorie = this.categorieService.read(article.getCategorie().getNoCategorie());
		article.setCategorie(categorie);

		Utilisateur vendeur = this.utilisateurService.lectureUtilisateur(article.getVendeur().getNoUtilisateur());
		article.setVendeur(vendeur);

		Retrait adresse = this.retraitService.read(article.getNoArticle());
		System.out.println(adresse);

		model.addAttribute("article", article);
		model.addAttribute("adresse", adresse);
		return "/view-encher-detail";
	}
}
