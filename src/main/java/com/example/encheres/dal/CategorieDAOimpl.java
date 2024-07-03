package com.example.encheres.dal;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.example.encheres.bo.Categorie;

@Repository
public class CategorieDAOimpl implements CategorieDAO {
	private NamedParameterJdbcTemplate jdbcTemplate;
	public CategorieDAOimpl(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private final static String CREATE   = "INSERT INTO CATEGORIE (libelle) VALUE (:libelle)";
	private final static String READ     = "SELECT no_categorie, libelle from CATEGORIES WHERE no_categorie = :noCategorie";
	private final static String UPDATE   = "UPDATE CATEGORIE SET libelle = :libelle";
	private final static String DELETE   = "DELETE FROM CATEGORIE WHERE no_categorie = :noCategorie";
	private final static String FIND_ALL = "SELECT no_categorie, libelle FROM CATEGORIES";
/**
 * creation categorie
 */
	@Override
	public void create(Categorie categorie) {
		MapSqlParameterSource mapParameterSource = new MapSqlParameterSource();
// ajout parametre pour la requete
		mapParameterSource.addValue("libelle",categorie.getLibelle());

         KeyHolder keyHolder = new GeneratedKeyHolder();


		jdbcTemplate.update(CREATE, mapParameterSource, keyHolder);
// recupération de la clef
		if (keyHolder != null && keyHolder.getKey() != null) {
			categorie.setNoCategorie(keyHolder.getKey().intValue());
		}
	}
/*
 *  lecture Categorie
 */
	@Override
	public Categorie read(int noCategorie) {
		MapSqlParameterSource mapParameterSource = new MapSqlParameterSource();
// ajout parametre pour la requete
		mapParameterSource.addValue("noCategorie",noCategorie);


		return jdbcTemplate.queryForObject(READ, mapParameterSource,new BeanPropertyRowMapper<>(Categorie.class));
	}
/**
 *  MAJ categorie
 */
	@Override
	public void update(Categorie categorie) {
		MapSqlParameterSource mapParameterSource = new MapSqlParameterSource();
// ajout parametre pour la requete
		mapParameterSource.addValue("libelle",categorie.getLibelle());

		jdbcTemplate.update(UPDATE, mapParameterSource);

	}
/*
 *  suppression categorie
 */
	@Override
	public void delete(int noCategorie) {
		MapSqlParameterSource mapParameterSource = new MapSqlParameterSource();
// ajout parametre pour la requete
		mapParameterSource.addValue("noCategorie",noCategorie);
		jdbcTemplate.update(DELETE, mapParameterSource);

	}
/**
 *  liste de toutes les categories
 */
	@Override
	public List<Categorie> findAll() {
		return jdbcTemplate.query(FIND_ALL ,new BeanPropertyRowMapper<>(Categorie.class));
	}



}
