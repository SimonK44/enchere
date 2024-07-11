package com.example.encheres.bll;

import java.util.List;

import com.example.encheres.bo.Utilisateur;
import com.example.encheres.exception.BusinessException;

public interface UtilisateurService {
// methodes sans les utilisateurs historisés
	void creerUtilisateur ( Utilisateur utilisateur)  throws BusinessException;
	Utilisateur lectureUtilisateur(int noUtilisateur);
	void modifierUtilisateur(Utilisateur utilisateur, String motDePasseActuel) throws BusinessException;
	void modifierUtilisateurCredit(int noUtilisateur, int credit);
	void supprimerUtilisateur(int noUtilisateur)      throws BusinessException;
	Utilisateur findByPseudo( String pseudo);
	List<Utilisateur> findAll ();
	// methodes avec les utilisateurs historisés
	List<Utilisateur> findAllHisto();
	void historiserUtilisateur (int noUtilisateur)    throws BusinessException;
}
