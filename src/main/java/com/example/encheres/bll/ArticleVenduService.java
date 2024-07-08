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
	void modifierArticleVenduPrixVente(int noArticleVendu, float prixVente);
	void supprimerArticleVendu(int articleVendu);
	void createArticleWithRetrait(ArticleVendu articleVendu, Retrait adresse, Utilisateur user);
	void encherirArticle(int noArticleVendu, float proposition, Utilisateur user);
	List<ArticleVendu> findAllComplexe(int requete,  String nomArticle, int noCategorie, int noUtilisateurVendeur, int noUtilisateurAcheteur);
}
