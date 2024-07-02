package eni.projet.enchere.bll;

import eni.projet.enchere.bo.Utilisateur;


public interface UtilisateurService {
	void creerUtilisateur ( Utilisateur utilisateur);
	Utilisateur lectureUtilisateur(int noUtilisateur);
	void modifierUtilisateur(Utilisateur utilisateur);
	void supprimerUtilisateur(int noUtilisateur);
}
