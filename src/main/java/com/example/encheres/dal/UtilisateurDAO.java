package com.example.encheres.dal;

import java.util.List;

import com.example.encheres.bo.Utilisateur;

public interface UtilisateurDAO {
	
	void create (Utilisateur utilisateur);
	Utilisateur read(int noUtlisateur);
	void update (Utilisateur utilisateur);
	void delete(int noUtilisateur);
	List<Utilisateur> findAll();
	
	Utilisateur findByPseudo(String pseudo); 
	
	int countByNomPrenom(String nom, String prenom);
	int countByPseudo(String pseudo);
	
	int countByNomPrenomModifier(int noUtilisateur, String nom, String prenom);
	int countByPseudoModifier(int noUtilisateur, String pseudo);
	int countByNoUtilisateur(int noUtilisateur);

}
