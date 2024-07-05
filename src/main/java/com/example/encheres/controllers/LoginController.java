package com.example.encheres.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.encheres.bll.contexte.ContexteService;
import com.example.encheres.bo.Utilisateur;

@Controller
@SessionAttributes({"utilisateurSession"})
public class LoginController {

	private ContexteService contexteService;

	public LoginController(ContexteService contexteService) {
		this.contexteService = contexteService;
	}

	//Affichage de la page de login
	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/session")
	public String connexion(@ModelAttribute("utilisateurSession") Utilisateur utilisateurSession) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();

		System.out.println("Session : "+currentPrincipalName);

		System.out.println("\n \n currentPrincipalName : " + currentPrincipalName);

		Utilisateur utilisateur = this.contexteService.charger(currentPrincipalName);

		System.out.println("utilisateurutilisateur : " + utilisateur);

		if(utilisateur != null) {
			//mettre à jour utilisateurSession avec l'utilisateur chargé
			utilisateurSession.setNoUtilisateur(utilisateur.getNoUtilisateur());
			utilisateurSession.setPseudo(utilisateur.getPseudo());
			utilisateurSession.setNom(utilisateur.getNom());
			utilisateurSession.setPrenom(utilisateur.getPrenom());
			utilisateurSession.setEmail(utilisateur.getEmail());
			utilisateurSession.setTelephone(utilisateur.getTelephone());
			utilisateurSession.setRue(utilisateur.getRue());
			utilisateurSession.setCodePostal(utilisateur.getCodePostal());
			utilisateurSession.setVille(utilisateur.getVille());
			utilisateurSession.setMotDePasse(utilisateur.getMotDePasse());
			utilisateurSession.setCredit(utilisateur.getCredit());
			utilisateurSession.setAdministrateur(utilisateur.isAdministrateur());

		}else {
			//mettre à jour utilisateurSession avec attributs null
			utilisateurSession.setNoUtilisateur(0);
			utilisateurSession.setPseudo(null);
			utilisateurSession.setNom(null);
			utilisateurSession.setPrenom(null);
			utilisateurSession.setEmail(null);
			utilisateurSession.setTelephone(null);
			utilisateurSession.setRue(null);
			utilisateurSession.setCodePostal(null);
			utilisateurSession.setVille(null);
			utilisateurSession.setMotDePasse(null);
			utilisateurSession.setCredit(0);
			utilisateurSession.setAdministrateur(false);
		}

		return "redirect:/home";
	}

	@ModelAttribute("utilisateurSession")
	public Utilisateur chargerUtilisateurSession() {
		return new Utilisateur();
	}


}
