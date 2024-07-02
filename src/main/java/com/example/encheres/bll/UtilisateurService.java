package com.example.encheres.bll;

import com.example.encheres.bo.Utilisateur;

public interface UtilisateurService {
	void creerUtilisateur ( Utilisateur utilisateur);
	Utilisateur lectureUtilisateur(int noUtilisateur);
	void modifierUtilisateur(Utilisateur utilisateur);
	void supprimerUtilisateur(int noUtilisateur);
}
