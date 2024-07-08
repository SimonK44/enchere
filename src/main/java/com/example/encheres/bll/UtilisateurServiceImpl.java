package com.example.encheres.bll;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
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
				
		cryptMotDePasse(utilisateur);
		
		boolean isValid = controleNomPrenom(utilisateur.getNom(),utilisateur.getPrenom(),be);
		isValid &= controlePseudo(utilisateur.getPseudo(), be);

		if (isValid) {
			
			try {				
				utilisateurDAO.create(utilisateur);
			} catch (DataAccessException e) {	
				System.out.println("utlisateur service pb Creation");
				be.addError(BusinessException.ERREUR_1);
				throw be;
			}
		} else {			
			throw be;
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
		
		cryptMotDePasse(utilisateur);
		
		boolean isValid = controleModifierNomPrenom(utilisateur.getNoUtilisateur(), utilisateur.getNom(),utilisateur.getPrenom(),be);
		isValid &= controleModifierPseudo(utilisateur.getNoUtilisateur(),utilisateur.getPseudo(),be );

		if (isValid) {
			try {
				utilisateurDAO.update(utilisateur);
				System.out.println("UtilisateurServImpl utilisateur : "+utilisateur);
			} catch (DataAccessException e) {
				e.printStackTrace();
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
	
	@Override
	public Utilisateur findByPseudo(String pseudo) {
		return utilisateurDAO.findByPseudo(pseudo);
		
	}
	
	@Override
	public List<Utilisateur> findAll() {
		return utilisateurDAO.findAll();
	}
	
	
	@Override
	public List<Utilisateur> findAllHisto() {
		return utilisateurDAO.findAllHisto();
	}

	@Override
	@Transactional(rollbackFor = BusinessException.class)
	public void historiserUtilisateur(int noUtilisateur) throws BusinessException {
		BusinessException be = new BusinessException() ;		
	
		try {
			utilisateurDAO.updateHisto(noUtilisateur);
		} catch (DataAccessException e) {
				e.printStackTrace();
				be.addError(BusinessException.ERREUR_0);
				throw be;
		}
	}

	private boolean controleNomPrenom (String nom, String prenom, BusinessException be) {
		boolean isValid = false;

		if (utilisateurDAO.countByNomPrenom(nom, prenom) == 0 ) {
		   isValid = true;		   
		} else {
			System.out.println("utlisateur service pb nom prenom");
			be.addError(BusinessException.ERREUR_1);
		}
		
		
		return isValid;
	}

	private boolean controlePseudo (String pseudo, BusinessException be) {
		boolean isValid = false;

		if (utilisateurDAO.countByPseudo(pseudo) == 0 ) {
		   isValid = true;
		} else {
			System.out.println("utlisateur service pb pseudo");
			be.addError(BusinessException.ERREUR_2);
		}
		
		return isValid;
	}

	private boolean controleModifierNomPrenom (int no_utilisateur,String nom, String prenom, BusinessException be) {
		boolean isValid = false;

		if (utilisateurDAO.countByNomPrenomModifier(no_utilisateur,nom, prenom) == 0 ) {
		   isValid = true;
		} else {
			be.addError(BusinessException.ERREUR_1);
		}
		
		return isValid;
	}

	private boolean controleModifierPseudo(int no_utilisateur,String pseudo, BusinessException be) {
		boolean isValid = false;

		if (utilisateurDAO.countByPseudoModifier(no_utilisateur,pseudo) == 0 ) {
		   isValid = true;
		} else {
			be.addError(BusinessException.ERREUR_2);
		}
		
		return isValid;
	}

	
	private void cryptMotDePasse(Utilisateur utilisateur) {
		System.out.println("generation mdp");
		utilisateur.setMotDePasse(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(utilisateur.getMotDePasse()));
		System.out.println(utilisateur.getMotDePasse());
	}


	

}
