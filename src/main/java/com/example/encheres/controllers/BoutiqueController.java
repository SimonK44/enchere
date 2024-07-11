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
		int credits = 0;
		switch (offre) {
			case "bronze":
				credits = 10000;
				break;
			case "gold":
				credits = 25000;
				break;
			case "platine":
				credits = 37500;
				break;
			case "diamand":
				credits = 50000;
				break;
			default:
				model.addAttribute("errorMessage", "Offre non valide");
				return "redirect:/boutique";
		}
		this.boutiqueService.update(utilisateurSession.getNoUtilisateur(), credits);
		return "redirect:/boutique";
	}
}
