package com.example.encheres.bll;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.encheres.bo.Utilisateur;
import com.example.encheres.dal.UtilisateurDAO;
import com.example.encheres.exception.BusinessException;


@Service
public class UtilisateurServiceImpl implements UtilisateurService {
	Logger logger =LoggerFactory.getLogger(UtilisateurService.class);
	
	@Autowired
	private UtilisateurDAO utilisateurDAO;

	@Autowired
    private PasswordEncoder passwordEncoder;	
	
	@Override
	@Transactional(rollbackFor = BusinessException.class)
	public void creerUtilisateur(Utilisateur utilisateur) throws BusinessException {
		BusinessException be = new BusinessException();

		boolean isValid = controleNomPrenom(utilisateur.getNom(),utilisateur.getPrenom(),be);
		isValid &= controlePseudo(utilisateur.getPseudo(), be);
		isValid &= controleEmail(utilisateur.getEmail(), be);
		isValid &= controleConfirmMotDePasse(utilisateur.getMotDePasse(), utilisateur.getConfirmMotDePasse(), be);

		cryptMotDePasse(utilisateur);

		if (isValid) {

			try {
				utilisateurDAO.create(utilisateur);
				this.logger.debug(BusinessException.LOGGER_6 + utilisateur.getNoUtilisateur() );
			} catch (DataAccessException e) {
				System.out.println("utlisateur service pb Creation");
				this.logger.error(BusinessException.LOGGER_7  + utilisateur.getNoUtilisateur() );	
				be.addError(BusinessException.ERREUR_1);
				throw be;
			}
		} else {
			System.out.println(be);
			throw be;
		}
	}

	@Override
	public Utilisateur lectureUtilisateur(int noUtilisateur) {
		return utilisateurDAO.read(noUtilisateur);

	}

	@Override
	@Transactional(rollbackFor = BusinessException.class)
	public void modifierUtilisateur(Utilisateur utilisateur, String motDePasseActuel) throws BusinessException {
		BusinessException be = new BusinessException() ;

		boolean isValid = controleModifierNomPrenom(utilisateur.getNoUtilisateur(), utilisateur.getNom(),utilisateur.getPrenom(),be);
		isValid &= controleModifierPseudo(utilisateur.getNoUtilisateur(),utilisateur.getPseudo(),be );
		isValid &= controleModifierEmail(utilisateur.getNoUtilisateur(), utilisateur.getEmail(), be);
		isValid &= controleMotDePasseActuel(motDePasseActuel, be);
		isValid &= controleConfirmMotDePasse(utilisateur.getMotDePasse(), utilisateur.getConfirmMotDePasse(), be);

		cryptMotDePasse(utilisateur);

		if (isValid) {
			try {
				utilisateurDAO.update(utilisateur);
				System.out.println("UtilisateurServImpl utilisateur : "+utilisateur);
				this.logger.debug(BusinessException.LOGGER_8  + utilisateur.getNoUtilisateur() );
			} catch (DataAccessException e) {
				e.printStackTrace();
				be.addError(BusinessException.ERREUR_0);
				this.logger.error(BusinessException.LOGGER_9 + utilisateur.getNoUtilisateur() );	
				System.out.println("Error");
				throw be;
			}
		} else {
			System.out.println("UtilisateurServImpl Error modif : "+utilisateur);
			throw be;
		}
	}
	@Override
	@Transactional(rollbackFor = BusinessException.class)
	public void modifierUtilisateurCredit(int noUtilisateur, int credit){
		utilisateurDAO.updateCredit(noUtilisateur, credit);
	}



	@Override
	@Transactional(rollbackFor = BusinessException.class)
	public void supprimerUtilisateur(int noUtilisateur) throws BusinessException {
		BusinessException be = new BusinessException() ;

		try {
			utilisateurDAO.delete(noUtilisateur);
			this.logger.debug(BusinessException.LOGGER_10 + noUtilisateur );
		} catch (DataAccessException e ) {
			be.addError(BusinessException.ERREUR_0);
			this.logger.error(BusinessException.LOGGER_11 + noUtilisateur );	
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
			this.logger.debug(BusinessException.LOGGER_12  + noUtilisateur );
		} catch (DataAccessException e) {
				e.printStackTrace();
				be.addError(BusinessException.ERREUR_0);
				this.logger.error(BusinessException.LOGGER_13  + noUtilisateur );	
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

	private boolean controleEmail (String email, BusinessException be) {
		boolean isValid = false;

		if (utilisateurDAO.countByMail(email) == 0 ) {
		   isValid = true;
		} else {
			System.out.println("utlisateur service pb mail");
			be.addError(BusinessException.ERREUR_7);
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
	
	private boolean controleModifierEmail(int no_utilisateur, String email, BusinessException be) {
		boolean isValid = false;

		if (utilisateurDAO.countByMailModifier(no_utilisateur, email) == 0) {
		   isValid = true;
		} else {
			System.out.println("utlisateur service pb mail modif");
			be.addError(BusinessException.ERREUR_7);
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

	private boolean controleConfirmMotDePasse(String motDePasse, String confMotDePasse, BusinessException be) {
		boolean isValid = false;

		if (motDePasse.equals(confMotDePasse)) {
		   isValid = true;
		} else {
			System.out.println("mot de passe : "+motDePasse+" conf : "+confMotDePasse);
			be.addError(BusinessException.ERREUR_6);
		}

		return isValid;
	}
	
	private boolean controleMotDePasseActuel(String motDePasseActuel, BusinessException be) {
		boolean isValid = false;
						
		// Vérification du mot de passe actuel
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        Utilisateur currentUser = this.findByPseudo(currentUsername);
        
		if (!motDePasseActuel.isBlank()) {	
			System.out.println("service : mdp non null");
			if (passwordEncoder.matches(motDePasseActuel, currentUser.getMotDePasse())) {
			   isValid = true;
			} else {
				System.out.println("(service) mot de passe actuel : "+motDePasseActuel+" confirmation : "+currentUser.getMotDePasse());
				be.addError(BusinessException.ERREUR_8);
			}
		} else {
			System.out.println("mot de passe actuel vide");
			be.addError(BusinessException.ERREUR_9);
		}

		return isValid;
	}
	

	private void cryptMotDePasse(Utilisateur utilisateur) {
		System.out.println("generation mdp");
		utilisateur.setMotDePasse(passwordEncoder.encode(utilisateur.getMotDePasse()));
		System.out.println(utilisateur.getMotDePasse());
	}

}
