package com.example.encheres.bll;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

import com.example.encheres.bo.Enchere;
import com.example.encheres.dal.ArticleVenduDAO;
import com.example.encheres.dal.EnchereDAO;
import com.example.encheres.dal.UtilisateurDAO;
import com.example.encheres.exception.BusinessException;

public class EnchereServiceImpl implements EnchereService {
	private EnchereDAO      enchereDAO;
	private ArticleVenduDAO articleVenduDAO;
	private UtilisateurDAO  utilisateurDAO;



/**
 *  creation d' une enchere
 */
	@Override
	@Transactional(rollbackFor = BusinessException.class)
	public void creerEnchere(Enchere enchere) throws BusinessException {
		/*BusinessException be = new BusinessException() ;

		boolean isValid = controleArticle(enchere.getArticleVendu().getNoArticle(), be);

		isValid &= controleUtilisateur(enchere.getUtilisateur().getNoUtilisateur(),be);

		isValid &= controleMontantMax(enchere.getArticleVendu().getNoArticle(), enchere.getMontantEnchere(),be);

		if (isValid) {

		  try {
			enchereDAO.create(enchere);
		  } catch (DataAccessException e) {
			be.addError(BusinessException.ERREUR_1);
			throw be;
		  }
		} else {
			throw be;
		}	*/
	}
/**
 * lecture enchere
 */
	@Override
	public Enchere lectureEnchere(int noUtilisateur, int noArticle) {

		return enchereDAO.read(noUtilisateur, noArticle);
	}
/**
 *  liste des encheres par article vendu
 */
	@Override
	public List<Enchere> listeEnchereParArticle(int noArticle) {

		return enchereDAO.findByArticle(noArticle);
	}

	@Override
	public Optional<Enchere> enchereMontantMax(int noArticle) {
		return enchereDAO.montantMax( noArticle);
	}

/**
 *  controle que l' article de l' enchere existe
 * @param noArticle
 * @param be
 * @return
 */
	private boolean controleArticle(int noArticle,BusinessException be) {
		boolean isValid = false;

		if ( articleVenduDAO.countArticle( noArticle) == 1 ) {
			isValid = true;
		}else {
				be.addError(BusinessException.ERREUR_3);
		}

		return isValid;

	}
/**
 * controle que l' utilisateur de l' enchere existe
 * @param noUtilisateur
 * @param be
 * @return
 */
	private boolean controleUtilisateur(int noUtilisateur,BusinessException be ) {
		boolean isValid = false;

		if (utilisateurDAO.countByNoUtilisateur(noUtilisateur)== 1) {
			isValid = true;
		}else {
			be.addError(BusinessException.ERREUR_4);
		}

		return isValid;
	}

/**
 *  controle que le montant de l' enchere depasse le montant de l' ancienne enchere
 */
/*	private boolean controleMontantMax( int noArticle, float montant, BusinessException be) {
		boolean isValid = false;

		if (enchereDAO.montantMax(noArticle) < montant) {
			isValid = true;
		} else {
			be.addError(BusinessException.ERREUR_5);
		}

		return isValid;

	}*/

}
