package com.example.encheres.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.encheres.bll.UtilisateurService;
import com.example.encheres.bo.Utilisateur;

@Controller
@SessionAttributes({"utilisateurSession"})
@RequestMapping("/administrateur")
public class AdministrateurController {
	
	private final static String LIST_UTILISATEUR = "view-utilisateur-liste";

	UtilisateurService utilisateurService;

	public AdministrateurController(UtilisateurService utilisateurService) {		
		this.utilisateurService = utilisateurService;
	}

	//Méthode permettant d'afficher la liste des utilisateur
	@GetMapping("/liste")
	public String afficherListeUtilisateur(Model model, @ModelAttribute("utilisateurSession") Utilisateur utilisateurSession) {
		List<Utilisateur> utilisateurs = utilisateurService.findAll();
		
		System.out.println("AdministrateurControlleur " + utilisateurs  );
		
		model.addAttribute("utilisateurs", utilisateurs);
		
		return LIST_UTILISATEUR;	
	}

}
