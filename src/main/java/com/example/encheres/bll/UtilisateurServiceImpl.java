package eni.projet.enchere.bll;

import org.springframework.stereotype.Service;

import eni.projet.enchere.bo.Utilisateur;
import eni.projet.enchere.dal.UtilisateurDAO;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {
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
		utilisateurDAO.update(utilisateur);
		
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

}
