package com.example.encheres.bll;

import java.util.List;

import com.example.encheres.bo.Enchere;
import com.example.encheres.exception.BusinessException;

public interface EnchereService {
	void creerEnchere ( Enchere enchere)                             throws BusinessException;
	Enchere lectureEnchere(int noUtilisateur, int noArticle);	
	List<Enchere> listeEnchereParArticle(int noArticle); 
	int enchereMontantMax(int noArticle);

}
