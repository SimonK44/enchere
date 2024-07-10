package com.example.encheres.controllers;

import com.example.encheres.bll.BoutiqueService;
import com.example.encheres.bo.Utilisateur;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes({"utilisateurSession"})
@RequestMapping("/boutique")
public class BoutiqueController {

	private final BoutiqueService boutiqueService;

	public BoutiqueController(BoutiqueService boutiqueService) {
		this.boutiqueService = boutiqueService;
	}

	@GetMapping("")
	public String homeAdmin() {
		return "view-boutique";
	}

	@PostMapping("/offres")
	public String acheterOffre(
			@RequestParam("type") String offre,
			Model model,
			@ModelAttribute("utilisateurSession") Utilisateur utilisateurSession
	) {
		int prix = 0;
		int credits = 0;

		switch (offre) {
			case "bronze":
				prix = 10;
				credits = 10000;
				break;
			case "gold":
				prix = 20;
				credits = 25000;
				break;
			case "platine":
				prix = 30;
				credits = 37500;
				break;
			case "diamand":
				prix = 40;
				credits = 50000;
				break;
			default:
				model.addAttribute("errorMessage", "Offre non valide");
				return "redirect:/boutique";
		}

		this.boutiqueService.update(utilisateurSession.getNoUtilisateur(), credits);
		model.addAttribute("successMessage", "Vous avez acheté l'offre " + offre + " pour " + prix + "€ et avez reçu " + credits + " crédits.");
		return "redirect:/boutique";
	}
}
