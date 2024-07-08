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

	UtilisateurService utilisateurService;
	CategorieService categorieService;

	public AdministrateurController(UtilisateurService utilisateurService, CategorieService categorieService) {
		this.utilisateurService = utilisateurService;
		this.categorieService = categorieService;
	}

	//Méthode permettant d'afficher la liste des utilisateur
	@GetMapping("")
	public String homeAdmin() {
		return "view-admin-home";
	}

	//Méthode permettant d'afficher la liste des utilisateurs
	@GetMapping("/liste")
	public String afficherListeUtilisateur(
			Model model,
			@ModelAttribute("utilisateurSession") Utilisateur utilisateurSession
	) {

		System.out.println(utilisateurSession);

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
		System.out.println("AdministrateurControlleur hisot no Utilisateur : " + noUtilisateur);
		
		try {
			utilisateurService.historiserUtilisateur(noUtilisateur);		
			return "redirect: " +LIST_UTILISATEUR;
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
		return "view-categorie-liste";
	}

	@GetMapping("/listeCategorieUpdate")
	public String modifDateSuppressionCategorieById(@RequestParam(value = "id", required = false) int noCategorie) {
		this.categorieService.updateDateSuppression(noCategorie);
		return "view-categorie-liste";
	}
	@GetMapping("/listeCategorieDelete")
	public String deleteCategorie(@RequestParam(value = "id", required = false) int noCategorie) {
		this.categorieService.delete(noCategorie);
		return "view-categorie-liste";
	}

}
