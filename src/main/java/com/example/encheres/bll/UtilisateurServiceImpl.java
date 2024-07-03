package com.example.encheres.bll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.encheres.bo.Utilisateur;
import com.example.encheres.dal.UtilisateurDAO;
import com.example.encheres.exception.BusinessException;


@Service
public class UtilisateurServiceImpl implements UtilisateurService {
	@Autowired
	private UtilisateurDAO utilisateurDAO;


	@Override
	@Transactional(rollbackFor = BusinessException.class)
	public void creerUtilisateur(Utilisateur utilisateur) throws BusinessException {
		BusinessException be = new BusinessException() ;
		
		boolean isValid = controleNomPrenom(utilisateur.getNom(),utilisateur.getPrenom(),be);
		isValid &= controlePseudo(utilisateur.getPseudo(), be);

		if (isValid) {
			
			try {
				utilisateurDAO.create(utilisateur);
			} catch (DataAccessException e) {
				// TODO Auto-generated catch block
				be.addError(BusinessException.ERREUR_1);
				throw be;
			}
		}

	}

	@Override
	public Utilisateur lectureUtilisateur(int noUtilisateur) {
		return utilisateurDAO.read(noUtilisateur);

	}

	@Override
	@Transactional(rollbackFor = BusinessException.class)
	public void modifierUtilisateur(Utilisateur utilisateur) throws BusinessException {
		BusinessException be = new BusinessException() ;
		
		boolean isValid = controleModifierNomPrenom(utilisateur.getNoUtilisateur(), utilisateur.getNom(),utilisateur.getPrenom(),be);
		isValid &= controleModifierPseudo(utilisateur.getNoUtilisateur(),utilisateur.getPseudo(),be );

		if (isValid) {
			try {
				utilisateurDAO.update(utilisateur);
			} catch (DataAccessException e) {
				be.addError(BusinessException.ERREUR_0);
				throw be;
			}
		}
	}

	

	@Override
	@Transactional(rollbackFor = BusinessException.class)
	public void supprimerUtilisateur(int noUtilisateur) throws BusinessException {
		BusinessException be = new BusinessException() ;
		
		try {
			utilisateurDAO.delete(noUtilisateur);
		} catch (DataAccessException e ) {
			be.addError(BusinessException.ERREUR_0);
			throw be;
		}

	}

	private boolean controleNomPrenom (String nom, String prenom, BusinessException be) {
		boolean isValid = false;

		if (utilisateurDAO.CountByNomPrenom(nom, prenom) == 0 ) {
		   isValid = true;		   
		} else {			
			be.addError(BusinessException.ERREUR_1);
		}
		
		
		return isValid;
	}

	private boolean controlePseudo (String pseudo, BusinessException be) {
		boolean isValid = false;

		if (utilisateurDAO.CountByPseudo(pseudo) == 0 ) {
		   isValid = true;
		} else {
			be.addError(BusinessException.ERREUR_2);
		}
		
		return isValid;
	}

	private boolean controleModifierNomPrenom (int no_utilisateur,String nom, String prenom, BusinessException be) {
		boolean isValid = false;

		if (utilisateurDAO.CountByNomPrenomModifier(no_utilisateur,nom, prenom) == 0 ) {
		   isValid = true;
		} else {
			be.addError(BusinessException.ERREUR_1);
		}
		
		return isValid;
	}

	private boolean controleModifierPseudo(int no_utilisateur,String pseudo, BusinessException be) {
		boolean isValid = false;

		if (utilisateurDAO.CountByPseudoModifier(no_utilisateur,pseudo) == 0 ) {
		   isValid = true;
		} else {
			be.addError(BusinessException.ERREUR_2);
		}
		
		return isValid;
	}

}
