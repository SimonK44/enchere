package eni.projet.enchere.dal;

import java.util.List;

import eni.projet.enchere.bo.Utilisateur;

public interface UtilisateurDAO {
	
	void create (Utilisateur utilisateur);
	Utilisateur read(int noUtlisateur);
	void update (Utilisateur utilisateur);
	void delete(int noUtilisateur);
	List<Utilisateur> findAll();
	int CountByNomPrenom(String nom, String prenom);
	int CountByPseudo(String pseudo);

}
