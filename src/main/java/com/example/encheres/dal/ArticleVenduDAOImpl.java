package com.example.encheres.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import com.example.encheres.bo.Categorie;
import com.example.encheres.bo.Utilisateur;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
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
	private final static String READ   = "SELECT no_article, nom_article, description, date_debut_encheres , date_fin_encheres, prix_initial, prix_vente, no_utilisateur_vendeur, no_utilisateur_acheteur , no_categorie FROM ARTICLES_VENDUS WHERE no_article = :noArticle ";
	private final static String FIND_ALL   = "SELECT no_article, nom_article, description, date_debut_encheres , date_fin_encheres, prix_initial, prix_vente, no_utilisateur_vendeur, no_utilisateur_acheteur , no_categorie, date_histo FROM ARTICLES_VENDUS ";
	private final static String UPDATE = "UPDATE ARTICLES_VENDUS SET nom_article = :nomArticle, description = :description, date_debut_encheres := dateDebutEncheres, date_fin_encheres := dateFinEncheres, prix_initial = :prixInitial, prix_vente = :prixVente, no_utilisateur_vendeur = :noUtilisateurVendeur, no_utilisateur_acheteur = noUtilisateurVendeur, no_categorie :=noCategorie";
	private final static String UPDATE_PRIX_VENTE = "UPDATE ARTICLES_VENDUS SET prix_vente = :prixVente WHERE no_article = :noArticle";
	private final static String UPDATE_ACHETEUR = "UPDATE ARTICLES_VENDUS SET no_utilisateur_acheteur = :noAcheteur WHERE no_article = :noArticle";
	private final static String UPDATE_RETRAIT = "UPDATE ARTICLES_VENDUS SET isRetrait = 1 WHERE no_article = :noArticle";
	
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
		mapParameterSource.addValue("noArticle", noArticle);
		return jdbcTemplate.queryForObject(READ, mapParameterSource, new ArticleVenduRowMapper());
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

	@Override
	public void updatePrixVente(int noArticle, int prixVente) {
		MapSqlParameterSource mapParameterSource = new MapSqlParameterSource();
		mapParameterSource.addValue("noArticle",noArticle);
		mapParameterSource.addValue("prixVente", prixVente);
		jdbcTemplate.update(UPDATE_PRIX_VENTE, mapParameterSource);
	}
	@Override
	public void updateAcheteur(int noArticle, int noAcheteur) {
		System.out.println("UpdateAcheteur");
		MapSqlParameterSource mapParameterSource = new MapSqlParameterSource();
		mapParameterSource.addValue("noArticle",noArticle);
		mapParameterSource.addValue("noAcheteur", noAcheteur);
		jdbcTemplate.update(UPDATE_ACHETEUR, mapParameterSource);
	}
	
	@Override
	public void updateRetrait(int noArticle) {
		System.out.println("UpdateAcheteur");
		MapSqlParameterSource mapParameterSource = new MapSqlParameterSource();
		mapParameterSource.addValue("noArticle",noArticle);		
		jdbcTemplate.update(UPDATE_RETRAIT, mapParameterSource);
		
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
		return jdbcTemplate.query(FIND_BY_UTILISATEUR,mapParameterSource,new BeanPropertyRowMapper<>(ArticleVendu.class));
	}

	@Override
	public List<ArticleVendu> findAll() {
		return jdbcTemplate.query(FIND_ALL, new ArticleVenduRowMapper());
	}

	/*
	 *  recherche article par categorie
	 */
	@Override
	public List<ArticleVendu> findByCategorie(int noCategorie) {
		MapSqlParameterSource mapParameterSource = new MapSqlParameterSource();
		mapParameterSource.addValue("no_categorie",noCategorie);
		return jdbcTemplate.query(FIND_BY_CATEGORIE,mapParameterSource,new BeanPropertyRowMapper<>(ArticleVendu.class));
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




	public class ArticleVenduRowMapper implements RowMapper<ArticleVendu> {

		@Override
		public ArticleVendu mapRow(ResultSet rs, int rowNum) throws SQLException {
			ArticleVendu articleVendu = new ArticleVendu();

			articleVendu.setNoArticle(rs.getInt("no_article"));
			articleVendu.setNomArticle(rs.getString("nom_article"));
			articleVendu.setDescription(rs.getString("description"));
			articleVendu.setDateDebutEnchere(rs.getObject("date_debut_encheres", LocalDate.class));
			articleVendu.setDateFinEnchere(rs.getObject("date_fin_encheres", LocalDate.class));
			articleVendu.setPrixInitial(rs.getInt("prix_initial"));
			articleVendu.setPrixVente(rs.getInt("prix_vente"));
			/*articleVendu.setDateHisto(rs.getObject("date_histo", LocalDate.class));*/

			// Map Categorie
			Categorie categorie = new Categorie();
			categorie.setNoCategorie(rs.getInt("no_categorie"));
			articleVendu.setCategorie(categorie);

			// Map Utilisateur Vendeur
			Utilisateur vendeur = new Utilisateur();
			vendeur.setNoUtilisateur(rs.getInt("no_utilisateur_vendeur"));
			articleVendu.setVendeur(vendeur);

			// Map Utilisateur Acheteur (peut être NULL)
			int noAcheteur = rs.getInt("no_utilisateur_acheteur");
			if (!rs.wasNull()) {
				Utilisateur acheteur = new Utilisateur();
				acheteur.setNoUtilisateur(noAcheteur);
				articleVendu.setAcheteur(acheteur);
			}

			return articleVendu;
		}
	}






}
