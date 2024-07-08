package com.example.encheres.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import com.example.encheres.bo.ArticleVendu;
import com.example.encheres.bo.Categorie;
import com.example.encheres.bo.Utilisateur;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.encheres.bo.Enchere;

@Repository
public class EnchereDAOImpl implements EnchereDAO {

	private NamedParameterJdbcTemplate jdbcTemplate;

	private static final String CREATE   = "INSERT INTO ENCHERES (no_utilisateur, no_article,date_enchere,montant_enchere) VALUES ( :noUtilisateur, :noArticle, GETDATE(), :montantEnchere)";
	private static final String READ     = "SELECT no_utilisateur, no_article,date_enchere,montant_enchere FROM ENCHERES WHERE no_utilisateur = :noUtilisateur and no_article = :noArticle ";
	private static final String UPDATE   = "UPDATE ENCHERES SET date_enchere = :datenEnchere , montant_enchere = :montantEnchere";
	private static final String DELETE   = "DELETE FROM ENCHERES WHERE no_utilisateur = :noUtilisateur and no_article = :noArticle";
	private static final String FIND_BY_UTILISATEUR = "SELECT no_utilisateur, no_article,date_enchere,montant_enchere FROM ENCHERES WHERE no_utilisateur = :noUtilisateur";
	private static final String FIND_BY_ARTICLE     = "SELECT no_utilisateur, no_article,date_enchere,montant_enchere FROM ENCHERES WHERE no_article = :noArticle";
	private static final String MONTANT_MAX = "SELECT * FROM ENCHERES where montant_enchere = (SELECT MAX(montant_enchere) FROM  ENCHERES where no_article = :noArticle);";

	public EnchereDAOImpl(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

/**
 * creation enchere
 */
	@Override
	public void create(Enchere enchere) {
		MapSqlParameterSource mapParameterSource = new MapSqlParameterSource();
// ajout parametres pour la requete
		System.out.println("\n \n MON ENCHERE : enchere" + enchere);
		mapParameterSource.addValue("noUtilisateur",enchere.getUtilisateur().getNoUtilisateur());
		mapParameterSource.addValue("noArticle",enchere.getArticleVendu().getNoArticle());
		mapParameterSource.addValue("montantEnchere",enchere.getMontantEnchere());

		jdbcTemplate.update(CREATE, mapParameterSource);

	}
/**
 * lecture enchere
 */
	@Override
	public Enchere read(int noUtilisateur, int noArticle) {
		MapSqlParameterSource mapParameterSource = new MapSqlParameterSource();
// ajout parametres pour la requete
		mapParameterSource.addValue("noUtilisateur",noUtilisateur);
		mapParameterSource.addValue("noArticle",noArticle);

		return jdbcTemplate.queryForObject(READ, mapParameterSource,new BeanPropertyRowMapper<>(Enchere.class));

	}
/**
 * MAJ enchere
 */
	@Override
	public void update(Enchere enchere) {
		MapSqlParameterSource mapParameterSource = new MapSqlParameterSource();
// ajout parametres pour la requete
		mapParameterSource.addValue("noUtilisateur",enchere.getUtilisateur().getNoUtilisateur());
		mapParameterSource.addValue("noArticle",enchere.getArticleVendu().getNoArticle());
		mapParameterSource.addValue("dateEnchere",enchere.getDateEnchere());
		mapParameterSource.addValue("montantEnchere",enchere.getMontantEnchere());

		jdbcTemplate.update(UPDATE, mapParameterSource);
	}
/**
 *  suppression enchere
 */
	@Override
	public void delete(int noUtilisateur, int noArticle) {
        MapSqlParameterSource mapParameterSource = new MapSqlParameterSource();
     // ajout parametres pour la requete
		mapParameterSource.addValue("noUtilisateur",noUtilisateur);
		jdbcTemplate.update(DELETE, mapParameterSource);

	}
/**
 * liste des encheres par utilisateur
 */
	@Override
	public List<Enchere> findByUtilisateur(int noUtilisateur) {
		MapSqlParameterSource mapParameterSource = new MapSqlParameterSource();
// ajout parametre pour la requete
		mapParameterSource.addValue("noUtilisateur",noUtilisateur);

		return jdbcTemplate.query(FIND_BY_UTILISATEUR ,new BeanPropertyRowMapper<>(Enchere.class));

	}
/**
 *  liste des encheres par articles
 */
	@Override
	public List<Enchere> findByArticle(int noArticle) {
		MapSqlParameterSource mapParameterSource = new MapSqlParameterSource();
// ajout parametre pour la requete
		mapParameterSource.addValue("noArticle",noArticle);

		return jdbcTemplate.query(FIND_BY_ARTICLE ,new BeanPropertyRowMapper<>(Enchere.class));

	}
/**
 *  montant max enchere pour un article vendu
 */
	@Override
	public Enchere montantMax(int noArticle) {
		MapSqlParameterSource mapParameterSource = new MapSqlParameterSource();
		mapParameterSource.addValue("noArticle",noArticle);
		System.out.println("int noArticle" + noArticle);
		return jdbcTemplate.queryForObject(MONTANT_MAX, mapParameterSource, new EnchereRowMapper());
	}


	public class EnchereRowMapper implements RowMapper<Enchere> {
		@Override
		public Enchere mapRow(ResultSet rs, int rowNum) throws SQLException {
			// Mapping utilisateur
			Utilisateur utilisateur = new Utilisateur();
			utilisateur.setNoUtilisateur(rs.getInt("no_utilisateur"));
			// Ajoutez les autres champs de Utilisateur ici

			// Mapping articleVendu
			ArticleVendu articleVendu = new ArticleVendu();
			articleVendu.setNoArticle(rs.getInt("no_article"));
			// Ajoutez les autres champs de ArticleVendu ici

			// Mapping enchere
			LocalDate dateEnchere = rs.getDate("date_enchere").toLocalDate();
			int montantEnchere = rs.getInt("montant_enchere");

			return new Enchere(utilisateur, articleVendu, dateEnchere, montantEnchere);
		}
	}


}
