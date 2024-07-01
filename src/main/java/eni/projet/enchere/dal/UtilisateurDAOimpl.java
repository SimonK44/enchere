package eni.projet.enchere.dal;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import eni.projet.enchere.bo.Utilisateur;


@Repository
public class UtilisateurDAOimpl implements UtilisateurDAO {
	
	private NamedParameterJdbcTemplate jdbcTemplate;	
	
	private static final String CREATE = "INSERT INTO UTILISATEURS ( pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) VALUES ( :pseudo, :nom, :prenom, :email, :telephone, :rue, :codePostal, :ville, :motDePasse, :credit, :administrateur)";
	private static final String READ   = "SELECT pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur FROM UTILISATEURS WHERE no_utilisateur = :noUtlisateur";
	private static final String UPDATE = "UPDATE UTILISATEURS  SET pseudo = :pseudo, nom = :nom, prenom = :prenom, email = :email, telephone = :telephone, rue = :rue, code_postal : codePostal, ville = : ville, mot_de_passe = :motDePasse, credit = :credit, administrateur = :administrateur WHERE no_utilisateur = noUtilisateur";
	private static final String DELETE = "";
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
			utilisateur.setNoUtlisateur(keyHolder.getKey().intValue());
		}
		
	}

	@Override
	public Utilisateur read(int noUtlisateur) {
		MapSqlParameterSource mapParameterSource = new MapSqlParameterSource();
		
		mapParameterSource.addValue("noUtlisateur",noUtlisateur);		
		
		return jdbcTemplate.queryForObject(READ, mapParameterSource,new BeanPropertyRowMapper<>(Utilisateur.class));
	}

	@Override
	public void update(Utilisateur utilisateur) {
		MapSqlParameterSource mapParameterSource = new MapSqlParameterSource();
		
		mapParameterSource.addValue("noUtlisateur",utilisateur.getNoUtlisateur());			
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
		
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		
		jdbcTemplate.update(CREATE, mapParameterSource, keyHolder);
		
	}

	@Override
	public void delete(int noUtlisateur) {
		// TODO Auto-generated method stub
		
	}

}
