package com.example.encheres.dal;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BoutiqueDAOimpl implements BoutiqueDAO {
	private final static String UPDATE   = "UPDATE UTILISATEURS SET credit = (credit + :credits) WHERE no_utilisateur = :noUtilisateur";
	private NamedParameterJdbcTemplate jdbcTemplate;
	public BoutiqueDAOimpl(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	@Override
	public void update(int noUtilisateur, int credits) {
		MapSqlParameterSource mapParameterSource = new MapSqlParameterSource();
		mapParameterSource.addValue("noUtilisateur", noUtilisateur);
		mapParameterSource.addValue("credits", credits);
		jdbcTemplate.update(UPDATE, mapParameterSource);
	}
}
