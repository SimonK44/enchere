package com.example.encheres.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UtilisateurControlleur {
	
	@GetMapping("/creer")
	public String creerUtilisateur() {		
		
		return "view-profil-creation";
	}
	
	@GetMapping("/afficher")
	public String afficherUtilisateurParId() {		
		
		return "view-utilisateur";
	}
	
	@GetMapping("/modifier")
	public String modifierUtilisateurParId() {		
		
		return "view-profil-modification";
	}
	
	
	
	
//	@PostMapping("/creer")
//	public String creerUtilisateur(@Valid @ModelAttribute("utilisateur") Utilisateur utilisateur, BindingResult bindingResult) {
//		
//		//S'il y a des erreurs, on retourne la vue
//		if (bindingResult.hasErrors()) {
//			return "view-profil-creation";
//		} else {		
//			//Appel du service en charge de la création de l'utilisateur
//			try {
//				this.utilisateur.add(utilisateur);
//				return "redirect:/utilisateurs";
//			} catch (BusinessException e) {
//				e.getErreurs().forEach(err -> {
//						ObjectError error = new ObjectError("globalError", err);
//						bindingResult.addError(error);
//					}
//				);
//				return "view-profil-creation";
//			}	
//		}		
//	}	

}
