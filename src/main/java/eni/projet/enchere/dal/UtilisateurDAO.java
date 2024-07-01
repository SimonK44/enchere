package eni.projet.enchere.dal;

import eni.projet.enchere.bo.Utilisateur;

public interface UtilisateurDAO {
	
	void create (Utilisateur utilisateur);
	Utilisateur read(int noUtlisateur);
	void update (Utilisateur utilisateur);
	void delete(int noUtlisateur);

}
