package com.example.encheres.dal;

import java.time.LocalDate;
import java.util.List;

import com.example.encheres.bo.ArticleVendu;


public interface ArticleVenduDynamiqueDAO {
	List<ArticleVendu> findDynamique (int requete, int NoCategorie,LocalDate dateRequete, int noUtilisateurVendeur, int no_UtilisateurAcheteur);
	

}
