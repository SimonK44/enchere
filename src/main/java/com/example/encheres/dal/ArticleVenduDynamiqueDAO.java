package eni.projet.enchere.dal;

import java.time.LocalDate;
import java.util.List;

import eni.projet.enchere.bo.ArticleVendu;


public interface ArticleVenduDynamiqueDAO {
	List<ArticleVendu> findDynamique (int requete, int NoCategorie,LocalDate dateRequete, int noUtilisateur );
	

}
