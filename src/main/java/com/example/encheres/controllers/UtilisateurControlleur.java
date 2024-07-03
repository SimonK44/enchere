package com.example.encheres.controllers;

import com.example.encheres.bll.UtilisateurService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.encheres.bo.Utilisateur;


@Controller
@SessionAttributes({"utilisateurSession"})
@RequestMapping("/utilisateurs")
public class UtilisateurControlleur {


	UtilisateurService utilisateurService;

	public UtilisateurControlleur (UtilisateurService utilisateurService) {
		this.utilisateurService = utilisateurService;
	}	
	
	//Méthode permettant d'afficher le formulaire de creation d'un utilisateur
	@GetMapping("/creer")
	public String creerUtilisateur(Model model) {
		//Etape 1 : Création d'une instance d'utilisateur
		Utilisateur utilisateur = new Utilisateur();
		
		//Etape 2 : Placer l'utilisateur dans le modèle
		model.addAttribute("utilisateur", utilisateur);
		
		return "view-profil-creation";
	}	

	@GetMapping("/modifier")
	public String modifierUtilisateurParId() {

		return "view-profil-modification";
	}

	@GetMapping("/afficher")
	public String afficherUtilisateurParId() {
		
//		Utilisateur u = this.utilisateurService.lectureUtilisateur(idUtilisateur);
//		System.out.println(u.getNoUtilisateur());
//		
//		model.addAttribute("utilisateur", u); // 1 objet utilisateur avec tous ses paramètres
		
		return "view-utilisateur";
	}	
	
	@PostMapping("/modifier")
	public String modifUtilisateurParId(@ModelAttribute("utilisateur") Utilisateur utilisateur, BindingResult bindingResult) {
		
		this.utilisateurService.modifierUtilisateur(utilisateur);
		
		return "view-profil-modification";	
	}
	
	
	@PostMapping("/creer")
	public String creerUtilisateur(@ModelAttribute("utilisateur") Utilisateur utilisateur) {	
		Utilisateur u = new Utilisateur();
		this.utilisateurService.creerUtilisateur(utilisateur);
		return "redirect:/utilisateurs/afficher";	
	}			


}
