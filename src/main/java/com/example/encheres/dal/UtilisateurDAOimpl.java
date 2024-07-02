package com.example.encheres.dal;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.example.encheres.bo.Utilisateur;


@Repository
public class UtilisateurDAOimpl implements UtilisateurDAO {
	
	private NamedParameterJdbcTemplate jdbcTemplate;	
	
	private static final String CREATE   = "INSERT INTO UTILISATEURS ( pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) VALUES ( :pseudo, :nom, :prenom, :email, :telephone, :rue, :codePostal, :ville, :motDePasse, :credit, :administrateur)";
	private static final String READ     = "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur FROM UTILISATEURS WHERE no_utilisateur = :noUtlisateur";
	private static final String UPDATE   = "UPDATE UTILISATEURS  SET pseudo = :pseudo, nom = :nom, prenom = :prenom, email = :email, telephone = :telephone, rue = :rue, code_postal : codePostal, ville = : ville, mot_de_passe = :motDePasse, credit = :credit, administrateur = :administrateur WHERE no_utilisateur = noUtilisateur";
	private static final String DELETE   = "DELETE FROM utilisateurs WHERE no_utilisateur = :noUtlisateur;";
	private static final String FIND_ALL = "SELECT pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur FROM UTILISATEURS";
    private static final String COUNT_BY_NOM_PRENOM    = "SELECT COUNT(*) WHERE nom = :nom AND prenom = :prenom";
    private static final String COUNT_BY_PSEUDO    = "SELECT COUNT(*) WHERE pseudo = :pseudo";
	/**
 * constructeur de UtlisateurDAOimpl	
 * @param jdbcTemplate
 */
public UtilisateurDAOimpl(NamedParameterJdbcTemplate jdbcTemplate) {		
		this.jdbcTemplate = jdbcTemplate;
	}

/**
 * creation d' un utilisateur	
 */
	@Override
	public void create(Utilisateur utilisateur) {
		MapSqlParameterSource mapParameterSource = new MapSqlParameterSource();
//		
		mapParameterSource.addValue("pseudo",utilisateur.getPseudo());
		mapParameterSource.addValue("nom",utilisateur.getNom());
		mapParameterSource.addValue("prenom",utilisateur.getPrenom());
		mapParameterSource.addValue("email",utilisateur.getEmail());
		mapParameterSource.addValue("telephone",utilisateur.getTelephone());
		mapParameterSource.addValue("rue",utilisateur.getRue());
		mapParameterSource.addValue("codePostal",utilisateur.getCodePostal());
		mapParameterSource.addValue("Ville",utilisateur.getVille());
		mapParameterSource.addValue("motDePasse",utilisateur.getMotDePasse());
		mapParameterSource.addValue("credit",utilisateur.getCredit());
		mapParameterSource.addValue("administrateur",utilisateur.isAdministrateur());
		
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
				
		jdbcTemplate.update(CREATE, mapParameterSource, keyHolder);
		
		if (keyHolder != null && keyHolder.getKey() != null) {
			utilisateur.setNoUtilisateur(keyHolder.getKey().intValue());
			
		}
		
	}
/**
 *  lecture utilisateur
 */
	@Override
	public Utilisateur read(int noUtilisateur) {
		MapSqlParameterSource mapParameterSource = new MapSqlParameterSource();
		
		mapParameterSource.addValue("noUtlisateur",noUtilisateur);		
		
		return jdbcTemplate.queryForObject(READ, mapParameterSource,new BeanPropertyRowMapper<>(Utilisateur.class));
	}
/**
 *  mise à jour utilisateur
 */
	@Override
	public void update(Utilisateur utilisateur) {
		MapSqlParameterSource mapParameterSource = new MapSqlParameterSource();
		
		mapParameterSource.addValue("noUtlisateur",utilisateur.getNoUtilisateur());			
		mapParameterSource.addValue("pseudo",utilisateur.getPseudo());
		mapParameterSource.addValue("pseudo",utilisateur.getPseudo());
		mapParameterSource.addValue("nom",utilisateur.getNom());
		mapParameterSource.addValue("prenom",utilisateur.getPrenom());
		mapParameterSource.addValue("email",utilisateur.getEmail());
		mapParameterSource.addValue("telephone",utilisateur.getTelephone());
		mapParameterSource.addValue("rue",utilisateur.getRue());
		mapParameterSource.addValue("codePostal",utilisateur.getCodePostal());
		mapParameterSource.addValue("Ville",utilisateur.getVille());
		mapParameterSource.addValue("motDePasse",utilisateur.getMotDePasse());
		mapParameterSource.addValue("credit",utilisateur.getCredit());
		mapParameterSource.addValue("administrateur",utilisateur.isAdministrateur());
		
		
		jdbcTemplate.update(UPDATE, mapParameterSource);
		
	}

	@Override
	public void delete(int noUtlisateur) {
		MapSqlParameterSource mapParameterSource = new MapSqlParameterSource();
		
		mapParameterSource.addValue("noUtlisateur",noUtlisateur);	
		jdbcTemplate.update(DELETE, mapParameterSource);
		
	}

	@Override
	public List<Utilisateur> findAll() {		
		return jdbcTemplate.query(FIND_ALL ,new BeanPropertyRowMapper<>(Utilisateur.class));	
	}


	@Override
	public int CountByNomPrenom(String nom, String prenom) {
		MapSqlParameterSource mapParameterSource = new MapSqlParameterSource();
		
		mapParameterSource.addValue("nom",nom);		
		mapParameterSource.addValue("prenom",prenom);		
		
		return jdbcTemplate.queryForObject(COUNT_BY_NOM_PRENOM , mapParameterSource, Integer.class) ;
	}

	@Override
	public int CountByPseudo(String pseudo) {
MapSqlParameterSource mapParameterSource = new MapSqlParameterSource();
		
		mapParameterSource.addValue("pseudo",pseudo);		
				
		
		return jdbcTemplate.queryForObject(COUNT_BY_PSEUDO , mapParameterSource, Integer.class) ;
	}

}