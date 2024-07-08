package com.example.encheres.bll;


import com.example.encheres.bo.ArticleVendu;
import com.example.encheres.bo.Categorie;
import com.example.encheres.exception.BusinessException;

import java.util.List;

public interface CategorieService {
	void create ( Categorie categorie);
	Categorie read(int noCategorie);
	void delete(int categorie)                  throws BusinessException;

	void update(int categorie)                  throws BusinessException;;
	void updateDateSuppression(int noCategorie) throws BusinessException;;
	List<Categorie> findAll();
	List<Categorie> findAllAdmin();
}
