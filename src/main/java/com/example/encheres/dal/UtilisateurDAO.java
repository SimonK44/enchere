package com.example.encheres.dal;

import java.util.List;

import com.example.encheres.bo.Utilisateur;

public interface UtilisateurDAO {

// methodes sans les utilisateurs historisés
	void create (Utilisateur utilisateur);
	Utilisateur read(int noUtlisateur);
	void update (Utilisateur utilisateur);
	void updateCredit (int noUtilisateur, float credit);
	void delete(int noUtilisateur);
	List<Utilisateur> findAll();
	Utilisateur findByPseudo(String pseudo);

	int countByNomPrenom(String nom, String prenom);
	int countByPseudo(String pseudo);

	int countByNomPrenomModifier(int noUtilisateur, String nom, String prenom);
	int countByPseudoModifier(int noUtilisateur, String pseudo);
	int countByNoUtilisateur(int noUtilisateur);

// methodes avec les utlisateurs historisés
	List<Utilisateur> findAllHisto();
	void updateHisto (int noUtilisateur);

}
