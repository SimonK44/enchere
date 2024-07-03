package com.example.encheres.bll;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.example.encheres.bo.Enchere;
import com.example.encheres.dal.ArticleVenduDAO;
import com.example.encheres.dal.EnchereDAO;
import com.example.encheres.dal.UtilisateurDAO;
import com.example.encheres.exception.BusinessException;

public class EnchereServiceImpl implements EnchereService {
	@Autowired
	private EnchereDAO      enchereDAO;
	private ArticleVenduDAO articleVenduDAO;
	private UtilisateurDAO  utilisateurDAO;

	
	
/**
 *  creation d' une enchere	
 */
	@Override
	public void creerEnchere(Enchere enchere) throws BusinessException {
		BusinessException be = new BusinessException() ;
		
		boolean isValid = controleArticle(enchere.getArticleVendu().getNoArticle(), be);
		
		isValid &= controleUtilisateur(enchere.getUtilisateur().getNoUtilisateur(),be);
		
		if (isValid) {
			
		  try {
			enchereDAO.create(enchere);
		  } catch (DataAccessException e) {
			be.addError(BusinessException.ERREUR_1);
			throw be;
		  }
		}
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
	public int enchereMontantMax(int noArticle) {
		
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


	
}
