package com.example.encheres.dal;

import java.time.LocalDate;
import java.util.List;

import com.example.encheres.bo.ArticleVendu;


public interface ArticleVenduDynamiqueDAO {
	List<ArticleVendu> findDynamique (int requete,  String nomArticle, int noCategorie, int noUtilisateurVendeur, int noUtilisateurAcheteur);
	

}
