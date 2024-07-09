
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
	
	private final static String ACHAT                   = "achat";
	private final static String VENTES                  = "ventes";
	
	private final static String SELECT = "SELECT nom_article, description, date_debut_encheres , date_fin_encheres, prix_initial, prix_vente, no_utilisateur_vendeur, no_utilisateur_acheteur , no_categorie FROM ARTICLES_VENDUS";
// union pour mes encheres en cours   
	private final static String UNION  = " AS A INNER JOIN ENCHERES AS E ON E.no_article = A.no_article" ; 
	
	private final static String WHERE = " WHERE";
	private final static String AND   = " AND";
	private final static String OR   = " OR";
	
// where des encheres_ouvertes
	private final static String ENCHERES_OUVERTES       = " (date_debut_encheres <= :dateDuJour AND date_fin_encheres  >= :dateDuJour)";
	private final static String MES_ENCHERES_EN_COURS   = " (date_debut_encheres <= :dateDuJour AND date_fin_encheres >= :dateDuJour AND E.no_utilisateur = :noUtilisateur) ";	
	private final static String MES_ENCHERES_REMPORTEES = " (date_fin_encheres < :dateDuJour AND no_utilisateur_acheteur = :noUtilisateurAcheteur)";
	
	private final static String MES_VENTES_EN_COURS     = " (date_debut_encheres >= :dateDuJour AND no_utilisateur_vendeur = :noUtilisateurVendeur)";
	private final static String VENTES_NON_DEBUTEES     = " (date_debut_encheres >= :dateDuJour AND no_utilisateur_vendeur = :noUtilisateurVendeur)";
	 private final static String VENTES_TERMINEES       = " (date_fin_encheres < :dateDujour AND no_utilisateur_vendeur = :noUtilisateurVendeur)"; 
	
	private final static String LIKE_NOM                = " AND nom_article like :nomArticle% ";
    private final static String CATEGORIE               = " AND no_categorie = :noCategorie ";		
      		
			
			
			
			
 //   private final static String ENCHERES_OUVERTES       = "SELECT nom_article, description, date_debut_encheres , date_fin_encheres, prix_initial, prix_vente, no_utilisateur_vendeur, no_utilisateur_acheteur , no_categorie FROM ARTICLES_VENDUS WHERE date_debut_encheres <= :dateDuJour AND date_fin_encheres  >= :dateDuJour";
 //   private final static String MES_ENCHERES_EN_COURS   = "SELECT nom_article, description, date_debut_encheres , date_fin_encheres, prix_initial, prix_vente, no_utilisateur_vendeur, no_utilisateur_acheteur , no_categorie FROM ARTICLES_VENDUS AS A INNER JOIN ENCHERES AS E ON E.no_article = A.no_article WHERE date_debut_encheres <= :dateDuJour AND date_fin_encheres >= :dateDuJour AND E.no_utilisateur = :noUtilisateur";
//    private final static String MES_ENCHERES_REMPORTEES = "SELECT nom_article, description, date_debut_encheres , date_fin_encheres, prix_initial, prix_vente, no_utilisateur_vendeur, no_utilisateur_acheteur , no_categorie FROM ARTICLES_VENDUS WHERE  date_fin_encheres < :dateDuJour AND no_utilisateur_acheteur = :noUtilisateurAcheteur" ;
//    private final static String MES_VENTES_EN_COURS     = "SELECT nom_article, description, date_debut_encheres , date_fin_encheres, prix_initial, prix_vente, no_utilisateur_vendeur, no_utilisateur_acheteur , no_categorie FROM ARTICLES_VENDUS WHERE  date_fin_encheres > :dateDuJour AND no_utilisateur_vendeur = :noUtilisateurVendeur ";
//    private final static String VENTES_NON_DEBUTEES     = "SELECT nom_article, description, date_debut_encheres , date_fin_encheres, prix_initial, prix_vente, no_utilisateur_vendeur, no_utilisateur_acheteur , no_categorie FROM ARTICLES_VENDUS WHERE date_debut_encheres >= :dateDuJour AND no_utilisateur_vendeur = :noUtilisateurVendeur" ;
//    private final static String VENTES_TERMINEES        = "SELECT nom_article, description, date_debut_encheres , date_fin_encheres, prix_initial, prix_vente, no_utilisateur_vendeur, no_utilisateur_acheteur , no_categorie FROM ARTICLES_VENDUS WHERE date_fin_encheres < :dateDujour AND no_utilisateur_vendeur = :noUtilisateurVendeur" ;

//    private final static String LIKE_NOM                = " AND nom_article like :nomArticle %";
//    private final static String CATEGORIE               = " AND no_categorie = :noCategorie";

	public ArticleVenduDynamiqueDAOImpl(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}




	@Override
	public List<ArticleVendu> findDynamique(String transactionType,int requete,  String nomArticle, int noCategorie, int noUtilisateurVendeur, int noUtilisateurAcheteur) {
		// ecriture de la requete
		String requeteFinale = preparationRequete( transactionType, requete, noCategorie, nomArticle, noUtilisateurVendeur, noUtilisateurAcheteur);
       // recuperation date du jour
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

		if (transactionType == ACHAT ) {
			switch (requete) {
// enchere ouverte
			case 1 :
				requeteFinale = SELECT + WHERE + ENCHERES_OUVERTES ;
				break;
// mes encheres en cours
			case 2 :
				requeteFinale = SELECT + UNION + WHERE + MES_ENCHERES_EN_COURS;
				break;
//  encheres ouvertes + mes encheres en cours
			case 3 :
				requeteFinale = SELECT + UNION + WHERE + MES_ENCHERES_EN_COURS + OR + ENCHERES_OUVERTES;
				break;
// mes encheres remportees
			case 4 :
				requeteFinale = SELECT +  WHERE + MES_ENCHERES_REMPORTEES;
				break;
// mes encheres ouvertes + mes encheres remportées
			case 5 :
				requeteFinale = SELECT + WHERE + ENCHERES_OUVERTES + OR + MES_ENCHERES_REMPORTEES;
				break;
// mes encheres en cours + mes encheres remportées
		case 6 :
				requeteFinale = SELECT + UNION + WHERE + MES_ENCHERES_EN_COURS + OR + MES_ENCHERES_REMPORTEES;
				break;
// mes enchere ouverte + mes encheres en cours + mes encheres remportées
			case 7 :
				requeteFinale = SELECT + UNION +  WHERE + ENCHERES_OUVERTES + OR + MES_ENCHERES_EN_COURS + OR + MES_ENCHERES_REMPORTEES;
				break;		
 			
			}
		} else { if (transactionType == VENTES ) { 
			switch (requete) {
// mes ventes en cours
			case 1 :
				requeteFinale = SELECT + WHERE + MES_VENTES_EN_COURS  ;
				break;
// ventes non debutées
			case 2 :
				requeteFinale = SELECT +  WHERE + VENTES_NON_DEBUTEES ;
				break;
//  mes ventes en cours + ventes non debutées
			case 3 :
				requeteFinale = SELECT + WHERE + MES_VENTES_EN_COURS + OR + VENTES_NON_DEBUTEES   ;
				break;
// Ventes terminées
			case 4 :
				requeteFinale = SELECT + WHERE + VENTES_TERMINEES  ;
				break;
// mes ventes en cours + ventes terminées
			case 5 :
				requeteFinale = SELECT + WHERE + MES_VENTES_EN_COURS + OR + VENTES_TERMINEES ;
				break;
// ventes non debutées + ventes terminées
		case 6 :
				requeteFinale = SELECT + WHERE + VENTES_NON_DEBUTEES + OR + VENTES_TERMINEES     ;
				break;
// mes ventes en cours + ventes non debutées + ventes terminées
			case 7 :
				requeteFinale = SELECT + WHERE + MES_VENTES_EN_COURS + OR + VENTES_NON_DEBUTEES + OR + VENTES_TERMINEES    ;
				break;		
// 			
			}	
			
			}
		}
		
		
// ajout du like sur le nom si besoin
		if ( nomArticle != null |  nomArticle != " " ) {
			requeteFinale += LIKE_NOM ;
		}
// ajout du filtre sur categorie
		if (noCategorie != 0) {
			requeteFinale += CATEGORIE;
		}
		
		System.out.println("ArticleVenduDynamiqueDAOImpl requeteFinale : " + requeteFinale);


		return requeteFinale;

	}


}

