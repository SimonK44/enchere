package com.example.encheres.controllers;

import com.example.encheres.bll.ArticleVenduService;
import com.example.encheres.bo.ArticleVendu;
import com.example.encheres.bo.Categorie;
import com.example.encheres.bo.Enchere;
import com.example.encheres.bo.Utilisateur;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ArticleVenduController {
	private ArticleVenduService articleVenduService;
	@Autowired
	public ArticleVenduController(ArticleVenduService articleVenduService) {
		this.articleVenduService = articleVenduService;
	}

	@GetMapping("/vendre-article")
	public String vendreArticle(Model model) {
		model.addAttribute("article", new ArticleVendu());
		return "view-encher-creation";
	}

	@PostMapping("/creer")
	public String creerArticle(@ModelAttribute("vendre-article") ArticleVendu articleVendu) {
		this.articleVenduService.create(articleVendu);
		return "redirect:/utilisateurs/afficher";
	}



	@GetMapping("/view-resultat-gagnant")
	public String resultatRetraitGagnant() {
		return "view-resultat-gagnant";
	}

	@GetMapping("/view-resultat-retrait")
	public String resultatRetrait() {
		return "view-resultat-retrait";
	}
}
