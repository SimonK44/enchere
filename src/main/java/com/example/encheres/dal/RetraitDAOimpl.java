package com.example.encheres.dal;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.encheres.bo.Retrait;


@Repository
public class RetraitDAOimpl implements RetraitDAO {
	private NamedParameterJdbcTemplate jdbcTemplate;

	private static final String CREATE = "INSERT INTO RETRAITS (no_article,rue, code_postal, ville) VALUES(:noArticle, :rue, :codePostal,:ville )";
	private static final String READ   = "SELECT no_article, rue, code_postal, ville FROM RETRAITS WHERE no_article = :noArticle";
	private static final String UPDATE = "UPDATE RETRAITS SET rue = :rue, code_postal = :codePostal, ville = :ville WHERE no_article = :noArticle";
	private static final String DELETE = "DELETE FROM RETRAITS WHERE no_article = :noArticle";



	public RetraitDAOimpl(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
/**
 * creation du retrait
 */
	@Override
	public void create(Retrait retrait) {
		MapSqlParameterSource mapParameterSource = new MapSqlParameterSource();
		// ajout parametres pour la requete
		mapParameterSource.addValue("noArticle",retrait.getNoArticle());
		mapParameterSource.addValue("rue",retrait.getRue());
		mapParameterSource.addValue("codePostal",retrait.getCodePostal());
		mapParameterSource.addValue("ville",retrait.getVille());
		jdbcTemplate.update(CREATE, mapParameterSource);
	}
/**
 *  lecture du retrait
 */

	@Override
	public Retrait read(int noArticle) {
		MapSqlParameterSource mapParameterSource = new MapSqlParameterSource();
// ajout parametre pour la requete
		mapParameterSource.addValue("noArticle",noArticle);

		return jdbcTemplate.queryForObject(READ, mapParameterSource,new BeanPropertyRowMapper<>(Retrait.class));
	}
/**
 *  MAJ retrait
 */
	@Override
	public void update(Retrait retrait) {
		MapSqlParameterSource mapParameterSource = new MapSqlParameterSource();
		mapParameterSource.addValue("noArticle",retrait.getNoArticle());
		mapParameterSource.addValue("rue",retrait.getRue());
		mapParameterSource.addValue("codePostal",retrait.getCodePostal());
		mapParameterSource.addValue("ville",retrait.getVille());
		jdbcTemplate.update(UPDATE, mapParameterSource);
	}
/**
 * suppression du retrait
 */
	@Override
	public void delete(int noArticle) {
		MapSqlParameterSource mapParameterSource = new MapSqlParameterSource();
// ajout parametre pour la requete
		mapParameterSource.addValue("noArticle",noArticle);
		jdbcTemplate.update(DELETE, mapParameterSource);

	}



}
