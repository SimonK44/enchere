package com.example.encheres.dal;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.example.encheres.bo.ArticleVendu;

@Repository
public class ArticleVenduDAOImpl implements ArticleVenduDAO {
	private NamedParameterJdbcTemplate jdbcTemplate;

	private final static String CREATE = "INSERT INTO ARTICLES_VENDUS (nom_article, description, date_debut_encheres , date_fin_encheres, prix_initial, prix_vente, no_utilisateur_vendeur, no_utilisateur_acheteur , no_categorie) VALUES (:nomArticle,:description,:dateDebutEnchere, :dateFinEnchere,:prixInitial, :prixVente ,:noUtilisateurVendeur, NULL, :noCategorie )";
    private final static String READ   = "SELECT nom_article, description, date_debut_encheres , date_fin_encheres, prix_initial, prix_vente, no_utilisateur_vendeur, no_utilisateur_acheteur , no_categorie FROM ARTICLES_VENDUS WHERE no_article = :noArticle ";
    private final static String UPDATE = "UPDATE ARTICLES_VENDUS SET nom_article = :nomArticle, description = :description, date_debut_encheres := dateDebutEncheres, date_fin_encheres := dateFinEncheres, prix_initial = :prixInitial, prix_vente = :prixVente, no_utilisateur_vendeur = :noUtilisateurVendeur, no_utilisateur_acheteur = noUtilisateurVendeur, no_categorie :=noCategorie";
    private final static String DELETE = "DELETE ARTICLES_VENDUS WHERE no_article = :noArticle";
	private final static String FIND_BY_UTILISATEUR = "SELECT nom_article, description, date_debut_encheres , date_fin_encheres, prix_initial, prix_vente, no_utilisateur_vendeur,no_utilisateur_acheteur , no_categorie FROM ARTICLES_VENDUS WHERE no_utilisateur_vendeur = :noUtilisateurVendeur";
	private final static String FIND_BY_CATEGORIE = "SELECT nom_article, description, date_debut_encheres , date_fin_encheres, prix_initial, prix_vente, no_utilisateur_vendeur, no_utilisateur_acheteur , no_categorie FROM ARTICLES_VENDUS WHERE no_categorie = :noCategorie";
    private final static String COUNT_BY_NOARTICLE = "SELECT COUNT(*) FROM ARTICLES_VENDUS WHERE no_article = :noArticle"; 



	public ArticleVenduDAOImpl(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
/**
 *  Creation d' un article vendu
 */
	@Override
	public void create(ArticleVendu articleVendu) {
		MapSqlParameterSource mapParameterSource = new MapSqlParameterSource();
// ajout parametres pour la requete
		mapParameterSource.addValue("nomArticle",articleVendu.getNomArticle());
		mapParameterSource.addValue("description",articleVendu.getDescription());
		mapParameterSource.addValue("dateDebutEnchere",articleVendu.getDateDebutEnchere());
		mapParameterSource.addValue("dateFinEnchere",articleVendu.getDateFinEnchere());
		mapParameterSource.addValue("prixInitial",articleVendu.getPrixInitial());
		mapParameterSource.addValue("prixVente",articleVendu.getPrixVente());
		mapParameterSource.addValue("noUtilisateurVendeur",articleVendu.getVendeur().getNoUtilisateur());
		mapParameterSource.addValue("noCategorie",articleVendu.getCategorie().getNoCategorie());
		KeyHolder keyHolder = new GeneratedKeyHolder();


		jdbcTemplate.update(CREATE, mapParameterSource, keyHolder);
		// recuperer la clef d' article vendu
		if (keyHolder != null && keyHolder.getKey() != null) {
			articleVendu.setNoArticle(keyHolder.getKey().intValue());
		}
	}
/**
 *  lecture avec le noArticle
 */
	@Override
	public ArticleVendu read(int noArticle) {
		MapSqlParameterSource mapParameterSource = new MapSqlParameterSource();
		// ajout parametre pour la requete
		mapParameterSource.addValue("noArticle",noArticle);
		return jdbcTemplate.queryForObject(READ, mapParameterSource,new BeanPropertyRowMapper<>(ArticleVendu.class));

	}

/**
 *  MAJ article Vendu
 */
	@Override
	public void update(ArticleVendu articleVendu) {
		MapSqlParameterSource mapParameterSource = new MapSqlParameterSource();
// ajout parametre pour la requete
		mapParameterSource.addValue("nomArticle",articleVendu.getNomArticle());
		mapParameterSource.addValue("description",articleVendu.getDescription());
		mapParameterSource.addValue("dateDebutEnchere",articleVendu.getDateDebutEnchere());
		mapParameterSource.addValue("dateFinEnchere",articleVendu.getDateFinEnchere());
		mapParameterSource.addValue("prixInitial",articleVendu.getPrixInitial());
		mapParameterSource.addValue("prixVente",articleVendu.getPrixVente());
		mapParameterSource.addValue("noUtilisateurVendeur",articleVendu.getVendeur().getNoUtilisateur());
		mapParameterSource.addValue("noUtilisateurAcheteur",articleVendu.getAcheteur().getNom());
		mapParameterSource.addValue("no_categorie",articleVendu.getCategorie().getNoCategorie());

		jdbcTemplate.update(UPDATE, mapParameterSource);
	}
/**
 *  delete Article Vendu
 */
	@Override
	public void delete(int noArticle) {
		MapSqlParameterSource mapParameterSource = new MapSqlParameterSource();
		mapParameterSource.addValue("noArticle",noArticle);
		jdbcTemplate.update(DELETE, mapParameterSource);
	}
/*
 *  recherche article vendu par numero d' utilisateur
 */
	@Override
	public List<ArticleVendu> findByUtilisateur(int noUtilisateur) {
		MapSqlParameterSource mapParameterSource = new MapSqlParameterSource();
// ajout parametre pour la requete
		mapParameterSource.addValue("no_utilisateur",noUtilisateur);
		return jdbcTemplate.query(FIND_BY_UTILISATEUR ,new BeanPropertyRowMapper<>(ArticleVendu.class));
	}
/*
 *  recherche article par categorie
 */
	@Override
	public List<ArticleVendu> findByCategorie(int noCategorie) {
		MapSqlParameterSource mapParameterSource = new MapSqlParameterSource();
		mapParameterSource.addValue("no_categorie",noCategorie);
		return jdbcTemplate.query(FIND_BY_CATEGORIE ,new BeanPropertyRowMapper<>(ArticleVendu.class));
	}
	
/**
 * comptage par no_article	
 */
	@Override
	public int countArticle(int noArticle) {
		MapSqlParameterSource mapParameterSource = new MapSqlParameterSource();	
		// ajout parametre pour la requete			
				mapParameterSource.addValue("no_article",noArticle);
				
				return jdbcTemplate.queryForObject(COUNT_BY_NOARTICLE , mapParameterSource, Integer.class) ;
	}
}
