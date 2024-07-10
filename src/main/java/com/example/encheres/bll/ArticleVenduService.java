package com.example.encheres.bll;

import com.example.encheres.bo.ArticleVendu;
import com.example.encheres.bo.Retrait;
import com.example.encheres.bo.Utilisateur;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ArticleVenduService {
	void create (ArticleVendu articleVendu);
	ArticleVendu lectureArticleVendu(int noArticleVendu);
	List<ArticleVendu> findAll();
	void modifierArticleVendu(ArticleVendu articleVendu);
	void modifierArticleVenduPrixVente(int noArticleVendu, int prixVente);
	void modifierArticleVenduVendeur(int noArticleVendu, int noVendeur);
	void supprimerArticleVendu(int articleVendu);
	void createArticleWithRetrait(ArticleVendu articleVendu, Retrait adresse, Utilisateur user, MultipartFile image);
	void encherirArticle(int noArticleVendu, int proposition, Utilisateur user);
	List<ArticleVendu> findAllComplexe(String transactionType, int requete,  String nomArticle, int noCategorie, int noUtilisateurVendeur, int noUtilisateurAcheteur);
}
