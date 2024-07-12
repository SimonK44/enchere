package com.example.encheres.controllers;

import com.example.encheres.bll.ArticleVenduService;
import com.example.encheres.bll.CategorieService;
import com.example.encheres.bll.EnchereService;
import com.example.encheres.bll.EnchereServiceImpl;
import com.example.encheres.bll.RetraitService;
import com.example.encheres.bll.UtilisateurService;
import com.example.encheres.bo.*;
import com.example.encheres.exception.BusinessException;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@SessionAttributes({"utilisateurSession"})
public class ArticleVenduController {
	private ArticleVenduService articleVenduService;
	private CategorieService categorieService;
	private UtilisateurService utilisateurService;
	private RetraitService retraitService;
	private EnchereService enchereService;


	@Autowired
	public ArticleVenduController(
			ArticleVenduService articleVenduService,
			CategorieService    categorieService,
			UtilisateurService  utilisateurService,
			RetraitService      retraitService,
			EnchereService      enchereService
	) {
		this.articleVenduService = articleVenduService;
		this.categorieService    = categorieService;
		this.utilisateurService  = utilisateurService;
		this.retraitService      = retraitService;
		this.enchereService      = enchereService;
	}

	@GetMapping("/vendre-article")
	public String vendreArticle(Model model) {
		ArticleVendu articleVendu = new ArticleVendu();
		articleVendu.setNomArticle("");
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

		if  (article.getVendeur().getNoUtilisateur() == user.getNoUtilisateur() && !article.isRetrait()) {
			model.addAttribute("beforeDateDebutEnchere", article.getDateDebutEnchere().isAfter(LocalDate.now()));
			model.addAttribute("isVendeur", true);
		} else {
			model.addAttribute("isVendeur", false);
			model.addAttribute("beforeDateDebutEnchere", false);
		}

		if (article.getAcheteur().getNoUtilisateur() == user.getNoUtilisateur()) {
			model.addAttribute("lastEnchereUser", true);
		} else {
			model.addAttribute("lastEnchereUser", false);
		}

		if (article.getDateFinEnchere().isBefore(LocalDate.now()) || article.getDateFinEnchere().isEqual(LocalDate.now())) {
			model.addAttribute("article", article);
			model.addAttribute("adresse", adresse);
			model.addAttribute("button", true);
			return "/view-encher-detail";
		} else {
			model.addAttribute("article", article);
			model.addAttribute("adresse", adresse);
			model.addAttribute("button", false);
			return "/view-encher-detail";
		}
	}

	@PostMapping("/modifier")
	public String modifierArticle(
			@ModelAttribute ArticleVendu article,
			@ModelAttribute("adresse") Retrait adresse
	) {
		articleVenduService.modifierArticleVendu(article);
		retraitService.update(adresse);
		return "redirect:view-encher-detail?id=" + article.getNoArticle();
	}


	@GetMapping("/modifier")
	public String afficherModifierArticle(
			@RequestParam(value = "id", required = false) int noArticleVendu,
			Model model,
			@ModelAttribute("utilisateurSession") Utilisateur user
	) {
		// Récupérer les informations de l'article
		ArticleVendu article = articleVenduService.lectureArticleVendu(noArticleVendu);
		if (article.getVendeur().getNoUtilisateur() != user.getNoUtilisateur()) {
			return "redirect:/view-encher-detail?id=" + noArticleVendu;
		}
		if ( article == null || LocalDate.now().isAfter(article.getDateDebutEnchere()) )  {
			return "redirect:/view-encher-detail?id=" + noArticleVendu;
		}

		// Récupérer la liste des catégories
		List<Categorie> categories = categorieService.findAll();
		Retrait adresse = this.retraitService.read(article.getNoArticle());

		// Ajouter les données au modèle
		model.addAttribute("article", article);
		model.addAttribute("categories", categories);

		// Ajouter l'adresse de retrait (si nécessaire)
		model.addAttribute("adresse", adresse);

		// Afficher la vue de modification de l'article
		return "view-encher-modification";
	}

	@PostMapping("/encherir")
	public String encherir(
		@ModelAttribute("utilisateurSession") Utilisateur utilisateurSession,
		@RequestParam("noArticleVendu") int noArticleVendu,
		@RequestParam("proposition") int proposition,
		RedirectAttributes redirectAttributes
	) {
		// PAS ASSER DE SOUS REDIRIGER BOUTIQUE
		Utilisateur utilisateur = this.utilisateurService.lectureUtilisateur(utilisateurSession.getNoUtilisateur());
		if (utilisateur.getCredit() < proposition) {
			return "redirect:/boutique";
		}
		// GESTION DE L'ENCHERE
		this.articleVenduService.encherirArticle(noArticleVendu, proposition, utilisateurSession);
		redirectAttributes.addFlashAttribute("errorMessage", "Une erreur est survenue lors de la soumission de l'enchère.");
		// Redirection ou affichage d'une nouvelle vue après traitement
		return "redirect:/view-encher-detail?id=" + noArticleVendu; // Redirige vers une page de confirmation
	}

	@GetMapping("/retrait")
	public String retrait (
			@RequestParam("noArticle") int noArticle,
			@ModelAttribute("utilisateurSession") Utilisateur utilisateurSession,
			BindingResult bingingResult
	) {

		try {
			articleVenduService.retirerArticle(noArticle);
			return "redirect:/view-encher-detail?id=" + noArticle;
		} catch (BusinessException e) {
			e.getErreurs().forEach(err -> {
				ObjectError error = new ObjectError("globalError", err);
				bingingResult.addError(error);
				}
			);

		return "redirect:/view-encher-detail?id=" + noArticle;
		}

	}

	@GetMapping("supprimer")
	public String supprimer (@RequestParam("noArticle") int noArticle,
			@ModelAttribute("utilisateurSession") Utilisateur utilisateurSession,
			BindingResult bingingResult) {
		ArticleVendu articleVendu = articleVenduService.lectureArticleVendu(noArticle);
		Optional<Enchere> lastEnchereMax = enchereService.enchereMontantMax(noArticle);
// si pas d' enchere
		if (lastEnchereMax.isEmpty()  ) {
			articleVenduService.supprimerArticleVendu(noArticle);
		} else {
				Utilisateur DernierAcheteur = utilisateurService.lectureUtilisateur(articleVendu.getAcheteur().getNoUtilisateur());
			   int montant =  lastEnchereMax.get().getMontantEnchere() + DernierAcheteur.getCredit();
			   utilisateurService.modifierUtilisateurCredit(articleVendu.getAcheteur().getNoUtilisateur(),
					                                        montant);
			   articleVenduService.supprimerArticleVendu(noArticle);
		}

		return "home";

	}
}
