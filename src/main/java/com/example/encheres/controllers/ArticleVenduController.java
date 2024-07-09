package com.example.encheres.controllers;

import com.example.encheres.bll.ArticleVenduService;
import com.example.encheres.bll.CategorieService;
import com.example.encheres.bll.RetraitService;
import com.example.encheres.bll.UtilisateurService;
import com.example.encheres.bo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

@Controller
@SessionAttributes({"utilisateurSession"})
public class ArticleVenduController {
	private ArticleVenduService articleVenduService;
	private CategorieService categorieService;
	private UtilisateurService utilisateurService;
	private RetraitService retraitService;

	@Value("${upload.path}")
	private String uploadPath;

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
		model.addAttribute("article", articleVendu);
		model.addAttribute("categories", categorieService.findAll());
		model.addAttribute("adresse", new Retrait());
		return "view-encher-creation";
	}

	@PostMapping("/creer")
	public String creerArticle(
		@ModelAttribute("vendre-article") ArticleVendu articleVendu,
		@ModelAttribute("adresse") Retrait adresse,
		@ModelAttribute("utilisateurSession") Utilisateur user,
		MultipartFile image
	) {
/*
		byte[] bytes = image.getBytes();
		Path path = Paths.get(uploadPath + image.getOriginalFilename());
		Files.write(path, bytes);
		article.setImagePath("/images/" + image.getOriginalFilename());*/
		this.articleVenduService.createArticleWithRetrait(articleVendu, adresse, user, image);
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
	public String pageArticleDetail(
			@RequestParam(value = "id", required = false) int noArticleVendu,
			Model model
	) {
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

	@PostMapping("/encherir")
	public String encherir(
		@ModelAttribute("utilisateurSession") Utilisateur utilisateurSession,
		@RequestParam("noArticleVendu") int noArticleVendu,
		@RequestParam("proposition") int proposition
	) {
		// Logique pour gérer l'enchère, par exemple enregistrer dans la base de données
		System.out.println("Enchère pour l'article avec ID : " + utilisateurSession);
		System.out.println("Enchère pour l'article avec ID : " + noArticleVendu);
		System.out.println("Proposition d'enchère : " + proposition);

		this.articleVenduService.encherirArticle(noArticleVendu, proposition, utilisateurSession);

		// Redirection ou affichage d'une nouvelle vue après traitement
		return "redirect:/view-encher-detail?id=" + noArticleVendu; // Redirige vers une page de confirmation
	}
}
