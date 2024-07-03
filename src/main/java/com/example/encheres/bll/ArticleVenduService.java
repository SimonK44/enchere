package com.example.encheres.bll;

import com.example.encheres.bo.ArticleVendu;
import com.example.encheres.bo.Utilisateur;
import org.springframework.stereotype.Service;

public interface ArticleVenduService {
	void create ( ArticleVendu articleVendu);
	ArticleVendu lectureArticleVendu(int noArticleVendu);
	void modifierArticleVendu(ArticleVendu articleVendu);
	void supprimerArticleVendu(int articleVendu);
}
