package com.example.encheres.controllers;

import com.example.encheres.bll.UtilisateurService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.encheres.bo.Utilisateur;
import com.example.encheres.exception.BusinessException;


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

	//Affichage de la page modification de profil
	@GetMapping("/modifier")
	public String modifierUtilisateurParId() {
		return "view-profil-modification";
	}

	//Afficher une page de profil simple
	@GetMapping("/afficher")
	public String afficherUtilisateurParId(@RequestParam(name="noUtilisateur", defaultValue ="1") int id, Model model, 
										@ModelAttribute("utilisateurSession") Utilisateur utilisateurSession) {
		
		if(utilisateurSession != null && utilisateurSession.getNoUtilisateur() >= 1) {
			// Il y a un utilsateur en session
			Utilisateur u = this.utilisateurService.lectureUtilisateur(id);
			System.out.println(u.getNoUtilisateur());	
			
			if(u != null) {
				model.addAttribute("utilisateur", u); // 1 objet utilisateur avec tous ses paramètres
			}
			return "view-utilisateur";			
		}		
		
		return "redirect:/login";		
		
	}	
	
	@PostMapping("/modifier")
	public String modifUtilisateurParId(@ModelAttribute("utilisateur") Utilisateur utilisateur) {
		
		try {
			this.utilisateurService.modifierUtilisateur(utilisateur);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "view-profil-modification";	
	}
	
	
	@PostMapping("/creer")
	public String creerUtilisateur(@ModelAttribute("utilisateur") Utilisateur utilisateur) {	
		Utilisateur u = new Utilisateur();
		try {
			this.utilisateurService.creerUtilisateur(utilisateur);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/utilisateurs/afficher";	
	}			


}
