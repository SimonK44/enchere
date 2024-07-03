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
	
	int CountByNomPrenom(String nom, String prenom);
	int CountByPseudo(String pseudo);
	
	int CountByNomPrenomModifier(int noUtilisateur, String nom, String prenom);
	int CountByPseudoModifier(int noUtilisateur, String pseudo);

}
