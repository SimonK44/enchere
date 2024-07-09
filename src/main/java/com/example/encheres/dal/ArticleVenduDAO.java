package com.example.encheres.dal;



import java.util.List;

import com.example.encheres.bo.ArticleVendu;

public interface ArticleVenduDAO {

	void create (ArticleVendu articleVendu);
	ArticleVendu read (int noArticle);
	void update (ArticleVendu articleVendu);
	void updatePrixVente (int noArticle, int prixVente);
	void delete (int noArticle);
	List<ArticleVendu> findByUtilisateur(int noUtilisateur);
	List<ArticleVendu> findAll();
	List<ArticleVendu> findByCategorie(int noCategorie);
	int countArticle ( int noArticle);


}
