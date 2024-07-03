package com.example.encheres.bll;

import com.example.encheres.bo.Enchere;
import com.example.encheres.exception.BusinessException;

public interface EnchereService {
	void creerEnchere ( Enchere enchere)                             throws BusinessException;
	Enchere lectureEnchere(int noUtilisateur, int noArticle);
	void modifierEnchere(Enchere enchere)                            throws BusinessException;
	void supprimerUtilisateur(int noUtilisateur, int noArticle)      throws BusinessException;

}
