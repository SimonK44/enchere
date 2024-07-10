package com.example.encheres.controllers;

import com.example.encheres.bll.ArticleVenduService;
import com.example.encheres.bll.CategorieService;
import com.example.encheres.bll.RetraitService;
import com.example.encheres.bll.UtilisateurService;
import com.example.encheres.bo.*;
import com.example.encheres.exception.BusinessException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
			Model model,
			@ModelAttribute("utilisateurSession") Utilisateur user
	) {
		ArticleVendu article = this.articleVenduService.lectureArticleVendu(noArticleVendu);

		Categorie categorie = this.categorieService.read(article.getCategorie().getNoCategorie());
		article.setCategorie(categorie);

		Utilisateur vendeur = this.utilisateurService.lectureUtilisateur(article.getVendeur().getNoUtilisateur());
		article.setVendeur(vendeur);

		Retrait adresse = this.retraitService.read(article.getNoArticle());
		System.out.println(adresse);

		boolean hideButtonEncherir = false;
		boolean lastEnchereUser    = false;
		boolean BoolVendeur        = false;

		System.out.println("\n \n Mon Article detail");
		System.out.println(article.getAcheteur().getNoUtilisateur());
		System.out.println("user : " + user.getNoUtilisateur());
		System.out.println("\n \n");


		System.out.println("article.getVendeur().getNoUtilisateur()" + article.getVendeur().getNoUtilisateur());
		System.out.println("user.getNoUtilisateur()" + user.getNoUtilisateur());
		
		if  (article.getVendeur().getNoUtilisateur() == user.getNoUtilisateur()) {
			BoolVendeur = true;
			System.out.println(" BoolVendeur true");
			model.addAttribute("BoolVendeur", BoolVendeur);
		}

		if (article.getAcheteur().getNoUtilisateur() == user.getNoUtilisateur()) {
			System.out.println("true");
			lastEnchereUser = true;
			model.addAttribute("lastEnchereUser", lastEnchereUser);
		}
		System.out.println("false");
		model.addAttribute("lastEnchereUser", lastEnchereUser);


		if (article.getDateFinEnchere().isBefore(LocalDate.now()) || article.getDateFinEnchere().isEqual(LocalDate.now())) {
			hideButtonEncherir = true;
			System.out.println("DAte exprirer");
			model.addAttribute("article", article);
			model.addAttribute("adresse", adresse);
			model.addAttribute("button", hideButtonEncherir);
			return "/view-encher-detail";
		} else {
			model.addAttribute("article", article);
			model.addAttribute("adresse", adresse);
			model.addAttribute("button", hideButtonEncherir);
			return "/view-encher-detail";
		}
	}

	@PostMapping("/encherir")
	public String encherir(
		@ModelAttribute("utilisateurSession") Utilisateur utilisateurSession,
		@RequestParam("noArticleVendu") int noArticleVendu,
		@RequestParam("proposition") int proposition,
		RedirectAttributes redirectAttributes
	) {

		// TODO IF PAS ASSER DE SOUS REDIRIGER BOUTIQUE


		// Logique pour gérer l'enchère, par exemple enregistrer dans la base de données
		System.out.println("Enchère pour l'article avec ID : " + utilisateurSession);
		System.out.println("Enchère pour l'article avec ID : " + noArticleVendu);
		System.out.println("Proposition d'enchère : " + proposition);

		this.articleVenduService.encherirArticle(noArticleVendu, proposition, utilisateurSession);
		redirectAttributes.addFlashAttribute("errorMessage", "Une erreur est survenue lors de la soumission de l'enchère.");
		// Redirection ou affichage d'une nouvelle vue après traitement
		return "redirect:/view-encher-detail?id=" + noArticleVendu; // Redirige vers une page de confirmation
	}
	
	@GetMapping("/retrait")
	public String retrait (@RequestParam("noArticle") int noArticle,
		                   @ModelAttribute("utilisateurSession") Utilisateur utilisateurSession,
		                   BindingResult bingingResult) {
		try {
			articleVenduService.retirerArticle(noArticle);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:/view-encher-detail?id=\" + noArticleVendu";
	}
	
	
}
