package eni.projet.enchere.dal;

import java.util.List;

import eni.projet.enchere.bo.ArticleVendu;

public interface ArticleVenduDAO {

	void create (ArticleVendu articleVendu);
	ArticleVendu read (int noArticle);
	void update (ArticleVendu articleVendu);
	void delete (int noArticle);
	List<ArticleVendu> findByUtilisateur(int noUtilisateur);
	List<ArticleVendu> findByCategorie(int noCategorie);
	
	
}
