package com.example.encheres.bll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.encheres.bo.Utilisateur;
import com.example.encheres.dal.UtilisateurDAO;


@Service
public class UtilisateurServiceImpl implements UtilisateurService {
	@Autowired
	private UtilisateurDAO utilisateurDAO;


	@Override
	public void creerUtilisateur(Utilisateur utilisateur) {
		boolean isValid = controleNomPrenom(utilisateur.getNom(),utilisateur.getPrenom());
		isValid &= controlePseudo(utilisateur.getPseudo());

		if (isValid) {
			utilisateurDAO.create(utilisateur);
		}

	}

	@Override
	public Utilisateur lectureUtilisateur(int noUtilisateur) {
		return utilisateurDAO.read(noUtilisateur);

	}

	@Override
	public void modifierUtilisateur(Utilisateur utilisateur) {
		boolean isValid = controleModifierNomPrenom(utilisateur.getNoUtilisateur(), utilisateur.getNom(),utilisateur.getPrenom());
		isValid &= controleModifierPseudo(utilisateur.getNoUtilisateur(),utilisateur.getPseudo());

		if (isValid) {
			utilisateurDAO.update(utilisateur);
		}
	}

	@Override
	public void supprimerUtilisateur(int noUtilisateur) {
		utilisateurDAO.delete(noUtilisateur);

	}

	private boolean controleNomPrenom (String nom, String prenom) {
		boolean isValid = false;

		if (utilisateurDAO.CountByNomPrenom(nom, prenom) == 0 ) {
		   isValid = true;
		}
		return isValid;
	}

	private boolean controlePseudo (String pseudo) {
		boolean isValid = false;

		if (utilisateurDAO.CountByPseudo(pseudo) == 0 ) {
		   isValid = true;
		}
		return isValid;
	}

	private boolean controleModifierNomPrenom (int no_utilisateur,String nom, String prenom) {
		boolean isValid = false;

		if (utilisateurDAO.CountByNomPrenomModifier(no_utilisateur,nom, prenom) == 0 ) {
		   isValid = true;
		}
		return isValid;
	}

	private boolean controleModifierPseudo (int no_utilisateur,String pseudo) {
		boolean isValid = false;

		if (utilisateurDAO.CountByPseudoModifier(no_utilisateur,pseudo) == 0 ) {
		   isValid = true;
		}
		return isValid;
	}

}
