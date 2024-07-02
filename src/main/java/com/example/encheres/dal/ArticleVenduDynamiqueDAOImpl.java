package eni.projet.enchere.dal;

import java.time.LocalDate;
import java.util.List;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import eni.projet.enchere.bo.ArticleVendu;

@Repository
public class ArticleVenduDynamiqueDAOImpl implements ArticleVenduDynamiqueDAO {
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	private final static String FIND_DEB         = "SELECT nom_article, description, date_debut_encheres , date_fin_encheres, prix_initial, prix_vente, no_utilisateur , no_categorie FROM ARTICLES_VENDUS AS A";
	private final static String INNER_JOIN       = " UNION JOIN ENCHERE AS E ON A.no_article = E.no_article AND A.no_utilisateur = E.no_utilisateur ";
	private final static String WHERE            = " WHERE ";
    private final static String NOM_ARTICLE_LIKE = "nom_article LIKE  :nomArticle% ";
    private final static String CATEGORIE        = "no_categorie = :noCategorie";  
    private final static String ENCHERE_EN_COURS = "date_debut_enchere <= :dateDuJour AND date_fin_enchere => :dateDuJour";
    private final static String MES_ENCHERE_REMPORTE = "no_utilisateur = :noUtilisateur ";
    
	
	
	
	
	
	public ArticleVenduDynamiqueDAOImpl(NamedParameterJdbcTemplate jdbcTemplate) {		
		this.jdbcTemplate = jdbcTemplate;
	}




	@Override
	public List<ArticleVendu> findDynamique(int requete, int NoCategorie, LocalDate dateRequete, int noUtilisateur) {
		// TODO Auto-generated method stub
		return null;
	}

}
