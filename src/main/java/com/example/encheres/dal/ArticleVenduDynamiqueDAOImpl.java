/*
package com.example.encheres.dal;

import java.time.LocalDate;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.encheres.bo.ArticleVendu;

@Repository
public class ArticleVenduDynamiqueDAOImpl implements ArticleVenduDynamiqueDAO {
	private NamedParameterJdbcTemplate jdbcTemplate;

	private final static String FIND_DEB         = "SELECT nom_article, description, date_debut_encheres , date_fin_encheres, prix_initial, prix_vente, no_utilisateur , no_categorie FROM ARTICLES_VENDUS AS A";
	private final static String INNER_JOIN       = " UNION JOIN ENCHERE AS E ON A.no_article = E.no_article AND A.no_utilisateur = E.no_utilisateur ";
	private final static String WHERE            = " WHERE ";
	private final static String AND              = " AND ";
    private final static String NOM_ARTICLE_LIKE = "nom_article LIKE  :nomArticle% ";
    private final static String CATEGORIE        = "no_categorie = :noCategorie";
    private final static String ENCHERE_EN_COURS = "date_debut_enchere <= :dateDuJour AND date_fin_enchere => :dateDuJour";
    private final static String MES_ENCHERES     = "no_utilisateur_acheteur = :noUtilisateur_acheteur ";
    private final static String MES_VENTES       = "no_utilisateur_vendeur = :noUtilisateur_vendeur ";





	public ArticleVenduDynamiqueDAOImpl(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}




	@Override
	public List<ArticleVendu> findDynamique(int requete, int NoCategorie, LocalDate dateRequete, int noUtilisateurVendeur, int noUtilisateurAcheteur) {
		// TODO Auto-generated method stub
		String requeteFinale = preparationRequete(requete, NoCategorie, dateRequete, noUtilisateurVendeur, noUtilisateurAcheteur);

		MapSqlParameterSource mapParameterSource = new MapSqlParameterSource();

		mapParameterSource.addValue("no_utilisateur_vendeur",noUtilisateurVendeur);
		mapParameterSource.addValue("no_utilisateur_acheteur",noUtilisateurAcheteur);

		return jdbcTemplate.query(requeteFinale,new BeanPropertyRowMapper<>(ArticleVendu.class));


	}

	private String preparationRequete (int requete, int NoCategorie, LocalDate dateRequete, int noUtilisateurVendeur, int noUtilisateurAcheteur) {
		String requeteFinale;

		switch (requete) {
// enchere ouverte
		case 1 :
			requeteFinale = FIND_DEB + WHERE + ENCHERE_EN_COURS;
			break;
// mes encheres en cours
		case 2 :
			requeteFinale = FIND_DEB + WHERE + ENCHERE_EN_COURS + AND + MES_VENTES ;
			break;
// mes encheres remportés

		}
		return requeteFinale;

	}


}
*/
