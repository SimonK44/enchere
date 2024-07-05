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

	//Méthode permettant d'afficher la liste des utilisateur
	@GetMapping("/liste")
	public String afficherListeUtilisateur(Model model, @ModelAttribute("utilisateurSession") Utilisateur utilisateurSession) {

		System.out.println(utilisateurSession);

		List<Utilisateur> utilisateurs = utilisateurService.findAll();

		System.out.println("AdministrateurControlleur " + utilisateurs  );

		model.addAttribute("utilisateurs", utilisateurs);

		return LIST_UTILISATEUR;
	}


	//Méthode permettant d'afficher la liste des utilisateur
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
