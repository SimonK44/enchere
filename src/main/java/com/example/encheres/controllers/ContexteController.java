//package com.example.encheres.controllers;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.SessionAttributes;
//
//import com.example.encheres.bll.contexte.ContexteService;
//import com.example.encheres.bo.Utilisateur;
//
//
//@Controller
//@RequestMapping("/contexte")
//@SessionAttributes({"utilisateurSession"})
//public class ContexteController {
//	
//private ContexteService contexteService;
//	
//	public ContexteController(ContexteService contexteService) {
//		this.contexteService = contexteService;
//	}
//
//	@GetMapping
//	public String afficherContexte() {
//		return "contexte/view-contexte";
//	}
//	
//	@GetMapping("/session")
//	public String connexion(@ModelAttribute("utilisateurSession") Utilisateur utilisateurSession, 
//							@RequestParam(name = "noUtilisateur", required = false) int noUtilisateur) {
//		
//		System.out.println("pseudo = " + pseudo);
//		
//		Utilisateur utilisateur = this.contexteService.charger(pseudo);
//		
//		if(utilisateur != null) {
//			//mettre à jour utilisateurSession avec l'utilisateur chargé
//			utilisateurSession.setNoUtilisateur(utilisateur.getNoUtilisateur());
//			utilisateurSession.setPseudo(utilisateur.getPseudo());
//			utilisateurSession.setNom(utilisateur.getNom());
//			utilisateurSession.setPrenom(utilisateur.getPrenom());
//			utilisateurSession.setEmail(utilisateur.getEmail());
//			utilisateurSession.setTelephone(utilisateur.getTelephone());
//			utilisateurSession.setRue(utilisateur.getRue());
//			utilisateurSession.setCodePostal(utilisateur.getCodePostal());
//			utilisateurSession.setVille(utilisateur.getVille());
//			utilisateurSession.setMotDePasse(utilisateur.getMotDePasse());
//			utilisateurSession.setCredit(utilisateur.getCredit());
//			utilisateurSession.setAdministrateur(utilisateur.isAdministrateur());
//
//		}else {
//			//mettre à jour utilisateurSession avec attributs null
//			utilisateurSession.setNoUtilisateur(0);
//			utilisateurSession.setPseudo(null);
//			utilisateurSession.setNom(null);
//			utilisateurSession.setPrenom(null);
//			utilisateurSession.setEmail(null);
//			utilisateurSession.setTelephone(null);
//			utilisateurSession.setRue(null);
//			utilisateurSession.setCodePostal(null);
//			utilisateurSession.setVille(null);
//			utilisateurSession.setMotDePasse(null);
//			utilisateurSession.setCredit(0);
//			utilisateurSession.setAdministrateur(false);
//		}
//		
//		return "redirect:/home";
//	}
//	
//	
////	@GetMapping("/cloture")
////	public String finSession(SessionStatus sessionStatus) {
////				
////		// vide tous les attributs de la session
////		sessionStatus.setComplete();
////		
////		// redirect obligatoire suite la destruction de la session
////		return "redirect:/home";
////	}
//	
//	
//	
//	@ModelAttribute("utilisateurSession")
//	public Utilisateur chargerUtilisateurSession() {
//		return new Utilisateur();
//	}	
//	
//}
