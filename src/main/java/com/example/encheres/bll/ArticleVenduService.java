package com.example.encheres.bll;

import com.example.encheres.bo.ArticleVendu;
import com.example.encheres.bo.Retrait;
import com.example.encheres.bo.Utilisateur;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ArticleVenduService {
	void create (ArticleVendu articleVendu);
	ArticleVendu lectureArticleVendu(int noArticleVendu);
	List<ArticleVendu> findAll();
	void modifierArticleVendu(ArticleVendu articleVendu);
	void supprimerArticleVendu(int articleVendu);

	void createArticleWithRetrait(ArticleVendu articleVendu, Retrait adresse);
}
