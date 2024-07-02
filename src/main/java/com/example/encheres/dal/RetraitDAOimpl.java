package com.example.encheres.dal;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.example.encheres.bo.Retrait;


@Repository
public class RetraitDAOimpl implements RetraitDao {
	private NamedParameterJdbcTemplate jdbcTemplate;	
	
	private static final String CREATE = "INSERT INTO RETRAITS (rue, code_postal, ville) VALUES( :rue, :codePostal,:ville )";
	private static final String READ   = "SELECT no_article, rue, code_postal, ville FROM RETRAITS WHERE no_article = :noArticles"; 
	private static final String UPDATE = "UPDATE RETRAIT SET rue = :rue, cod_postal = :codePostal, ville = :ville";
	private static final String DELETE = "DELETE FROM RETRAIT WHERE no_article = :noArticle";
	
	
	
	public RetraitDAOimpl(NamedParameterJdbcTemplate jdbcTemplate) {		
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void create(Retrait retrait) {
		MapSqlParameterSource mapParameterSource = new MapSqlParameterSource();
//		
		mapParameterSource.addValue("rue",retrait.getRue());
		mapParameterSource.addValue("codePostal",retrait.getCodePostal());
		mapParameterSource.addValue("ville",retrait.getVille());
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		
		jdbcTemplate.update(CREATE, mapParameterSource, keyHolder);
		
		if (keyHolder != null && keyHolder.getKey() != null) {
			retrait.setNoArticle(keyHolder.getKey().intValue());
		}
		
		
	}

	@Override
	public Retrait read(int noArticle) {
		MapSqlParameterSource mapParameterSource = new MapSqlParameterSource();
		
		mapParameterSource.addValue("noArticle",noArticle);
		
		return jdbcTemplate.queryForObject(READ, mapParameterSource,new BeanPropertyRowMapper<>(Retrait.class));
	}

	@Override
	public void update(Retrait retrait) {
		MapSqlParameterSource mapParameterSource = new MapSqlParameterSource();
		
		mapParameterSource.addValue("noArticle",retrait.getNoArticle());
		mapParameterSource.addValue("rue",retrait.getRue());
		mapParameterSource.addValue("codePostal",retrait.getCodePostal());
		mapParameterSource.addValue("ville",retrait.getVille());
		
		jdbcTemplate.update(UPDATE, mapParameterSource);
		
	}

	@Override
	public void delete(int noArticle) {
		MapSqlParameterSource mapParameterSource = new MapSqlParameterSource();
		
		mapParameterSource.addValue("noArticle",noArticle);
		jdbcTemplate.update(DELETE, mapParameterSource);		
		
	}

	

}
