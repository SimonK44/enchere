package com.example.encheres.bll;

import com.example.encheres.bo.ArticleVendu;
import com.example.encheres.bo.Utilisateur;

public interface ArticleVenduService {
	void creerArticleVendu ( ArticleVendu articleVendu);
	ArticleVendu lectureArticleVendu(int noArticleVendu);
	void modifierArticleVendu(ArticleVendu articleVendu);
	void supprimerArticleVendu(int articleVendu);
}
