
package com.example.encheres.dal;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.encheres.bo.ArticleVendu;

@Repository
public class ArticleVenduDynamiqueDAOImpl implements ArticleVenduDynamiqueDAO {
	private NamedParameterJdbcTemplate jdbcTemplate;

    private final static String ENCHERES_OUVERTES       = "SELECT nom_article, description, date_debut_encheres , date_fin_encheres, prix_initial, prix_vente, no_utilisateur_vendeur, no_utilisateur_acheteur , no_categorie FROM ARTICLES_VENDUS WHERE date_debut_encheres <= :dateDuJour AND date_fin_encheres  >= :dateDuJour";
    private final static String MES_ENCHERES_EN_COURS   = "SELECT nom_article, description, date_debut_encheres , date_fin_encheres, prix_initial, prix_vente, no_utilisateur_vendeur, no_utilisateur_acheteur , no_categorie FROM ARTICLES_VENDUS AS A INNER JOIN ENCHERES AS E ON E.no_article = A.no_article WHERE date_debut_encheres <= :dateDuJour AND date_fin_encheres >= :dateDuJour AND E.no_utilisateur = :noUtilisateur";
    private final static String MES_ENCHERES_REMPORTEES = "SELECT nom_article, description, date_debut_encheres , date_fin_encheres, prix_initial, prix_vente, no_utilisateur_vendeur, no_utilisateur_acheteur , no_categorie FROM ARTICLES_VENDUS WHERE  date_fin_encheres < :dateDuJour AND no_utilisateur_acheteur = :noUtilisateurAcheteur" ;
    private final static String MES_VENTES_EN_COURS     = "SELECT nom_article, description, date_debut_encheres , date_fin_encheres, prix_initial, prix_vente, no_utilisateur_vendeur, no_utilisateur_acheteur , no_categorie FROM ARTICLES_VENDUS WHERE  date_fin_encheres > :dateDuJour AND no_utilisateur_vendeur = :noUtilisateurVendeur ";
    private final static String VENTES_NON_DEBUTEES     = "SELECT nom_article, description, date_debut_encheres , date_fin_encheres, prix_initial, prix_vente, no_utilisateur_vendeur, no_utilisateur_acheteur , no_categorie FROM ARTICLES_VENDUS WHERE date_debut_encheres >= :dateDuJour AND no_utilisateur_vendeur = :noUtilisateurVendeur" ;
    private final static String VENTES_TERMINEES        = "SELECT nom_article, description, date_debut_encheres , date_fin_encheres, prix_initial, prix_vente, no_utilisateur_vendeur, no_utilisateur_acheteur , no_categorie FROM ARTICLES_VENDUS WHERE date_fin_encheres < :dateDujour AND no_utilisateur_vendeur = :noUtilisateurVendeur" ;

    private final static String LIKE_NOM                = " AND nom_article like :nomArticle %";
    private final static String CATEGORIE               = " AND no_categorie = :noCategorie";

	public ArticleVenduDynamiqueDAOImpl(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}




	@Override
	public List<ArticleVendu> findDynamique(String transactionType,int requete,  String nomArticle, int noCategorie, int noUtilisateurVendeur, int noUtilisateurAcheteur) {
		// ecriture de la requete
		String requeteFinale = preparationRequete( transactionType, requete, noCategorie, nomArticle, noUtilisateurVendeur, noUtilisateurAcheteur);
       // recuperation date du jour
		System.out.println(requeteFinale);
		String dateDuJour = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-mm-dd"));
		MapSqlParameterSource mapParameterSource = new MapSqlParameterSource();

		mapParameterSource.addValue("dateDuJour",dateDuJour);
		mapParameterSource.addValue("noCategorie",noCategorie);
		mapParameterSource.addValue("nomArticle",nomArticle);
		mapParameterSource.addValue("no_utilisateur_vendeur",noUtilisateurVendeur);
		//mapParameterSource.addValue("no_utilisateur_vendeur",noUtilisateurVendeur);
		mapParameterSource.addValue("no_utilisateur_acheteur",noUtilisateurAcheteur);

		return jdbcTemplate.query(requeteFinale,new BeanPropertyRowMapper<>(ArticleVendu.class));


	}

	private String preparationRequete (String transactionType,int requete, int noCategorie, String nomArticle, int noUtilisateurVendeur, int noUtilisateurAcheteur) {
		String requeteFinale = "";

		switch (requete) {
// enchere ouverte
		case 1 :
			requeteFinale = ENCHERES_OUVERTES;
			break;
// mes encheres en cours
		case 2 :
			requeteFinale = MES_ENCHERES_EN_COURS  ;
			break;
// mes encheres remportés
		case 3 :
			requeteFinale = MES_ENCHERES_REMPORTEES  ;
			break;
// mes ventes en cours
		case 4 :
			requeteFinale = MES_VENTES_EN_COURS  ;
			break;
// mes ventes debutées
		case 5 :
			requeteFinale = VENTES_NON_DEBUTEES   ;
			break;
// mes ventes terminées
		case 6 :
			requeteFinale = VENTES_TERMINEES    ;
			break;
		}
// ajout du like sur le nom si besoin
		if ( nomArticle != null |  nomArticle != " " ) {
			requeteFinale += LIKE_NOM ;
		}
// ajout du filtre sur categorie
		if (noCategorie != 0) {
			requeteFinale += CATEGORIE;
		}


		return requeteFinale;

	}


}

