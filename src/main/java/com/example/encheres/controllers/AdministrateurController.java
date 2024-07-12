package com.example.encheres.controllers;

import java.util.List;

import com.example.encheres.bll.CategorieService;
import com.example.encheres.bo.Categorie;
import com.example.encheres.exception.BusinessException;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import com.example.encheres.bll.UtilisateurService;
import com.example.encheres.bo.Utilisateur;

@Controller
@SessionAttributes({"utilisateurSession"})
@RequestMapping("/administrateur")
public class AdministrateurController {

	private final static String LIST_UTILISATEUR = "view-utilisateur-liste";
	private final static String LIST_CATEGORIE   = "view-categorie-liste";

	UtilisateurService utilisateurService;
	CategorieService categorieService;

	public AdministrateurController(
			UtilisateurService utilisateurService,
			CategorieService categorieService
	) {
		this.utilisateurService = utilisateurService;
		this.categorieService = categorieService;
	}

	@GetMapping("")
	public String homeAdmin() {
		return "view-admin-home";
	}

	// Méthode permettant d'afficher la liste des utilisateurs
	@GetMapping("/liste")
	public String afficherListeUtilisateur(
			Model model,
			@ModelAttribute("utilisateurSession") Utilisateur utilisateurSession
	) {
		List<Utilisateur> utilisateurs = utilisateurService.findAllHisto();
		model.addAttribute("utilisateurs", utilisateurs);
		return LIST_UTILISATEUR;
	}

	// methode permettant d' historiser les utilisateurs
	@GetMapping("/supprime")
	public String historiserUtilisateur (
			@RequestParam (name="noUtilisateur") int noUtilisateur,
			@ModelAttribute("utilisateurSession") Utilisateur utilisateurSession,
			BindingResult bingingResult
			) {
		try {
			utilisateurService.historiserUtilisateur(noUtilisateur);			
			return "redirect:liste";
		} catch (BusinessException e) {
			e.getErreurs().forEach(err -> {
				ObjectError error = new ObjectError("globalError", err);
				bingingResult.addError(error);
				}
			);
			return LIST_UTILISATEUR;
		}
	}

	//Méthode permettant d'afficher la liste des categories
	@GetMapping("/listeCategorie")
	public String afficherListeCategorie(Model model) {
		List<Categorie> categories = this.categorieService.findAllAdmin();
		model.addAttribute("categories", categories);
		return LIST_CATEGORIE;
	}

	@GetMapping("/listeCategorieUpdate")
	public String modifDateSuppressionCategorieById(
			@RequestParam(value = "id", required = false) int noCategorie,
			@ModelAttribute("utilisateurSession") Utilisateur utilisateurSession,
			BindingResult bingingResult
	) {
		try {
			this.categorieService.updateDateSuppression(noCategorie);
			return "redirect:" + "/administrateur/listeCategorie";
		} catch (BusinessException e) {
			e.printStackTrace();
			e.getErreurs().forEach(err -> {
				ObjectError error = new ObjectError("globalError", err);
				bingingResult.addError(error);
				}
			);
			return "redirect:" + "/administrateur/listeCategorie";
		}
	}

	@GetMapping("/listeCategorieDelete")
	public String deleteCategorie(
			@RequestParam(value = "id", required = false) int noCategorie,
			@ModelAttribute("utilisateurSession") Utilisateur utilisateurSession,
			BindingResult bingingResult
	) {
		try {
			this.categorieService.delete(noCategorie);
			return "redirect:" + "/administrateur/listeCategorie";
		} catch (BusinessException e) {
			e.printStackTrace();
			e.getErreurs().forEach(err -> {
				ObjectError error = new ObjectError("globalError", err);
				bingingResult.addError(error);
				}
			);
			return "redirect:" + "/administrateur/listeCategorie";
		}
	}


	@PostMapping("/creerCategorie")
	public String creerCategorie(@RequestParam("libelle") String libelle, Model model) {
		Categorie nouvelleCategorie = new Categorie();
		nouvelleCategorie.setLibelle(libelle);
		categorieService.create(nouvelleCategorie);
		return "redirect:/administrateur/listeCategorie"; // Redirige vers la liste des catégories
	}
}
