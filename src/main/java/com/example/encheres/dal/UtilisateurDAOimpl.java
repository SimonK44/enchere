package com.example.encheres.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.example.encheres.bo.Utilisateur;


@Repository
public class UtilisateurDAOimpl implements UtilisateurDAO {
	private NamedParameterJdbcTemplate jdbcTemplate;
// requete SQL sans histo
	private static final String CREATE   = "INSERT INTO UTILISATEURS ( pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) VALUES ( :pseudo, :nom, :prenom, :email, :telephone, :rue, :codePostal, :ville, :motDePasse, :credit, :administrateur)";
	private static final String READ     = "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur FROM UTILISATEURS WHERE no_utilisateur = :noUtlisateur";
	private static final String UPDATE   = "UPDATE UTILISATEURS SET pseudo = :pseudo, nom = :nom, prenom = :prenom, email = :email, telephone = :telephone, rue = :rue, code_postal = :codePostal, ville = :ville, mot_de_passe = :motDePasse WHERE no_utilisateur = :noUtilisateur";
	private static final String UPDATE_CREDIT   = "UPDATE UTILISATEURS SET credit = :credit WHERE no_utilisateur = :noUtilisateur";
	private static final String DELETE   = "DELETE FROM utilisateurs WHERE no_utilisateur = :noUtilisateur";
	private static final String FIND_ALL = "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur FROM UTILISATEURS ORDER BY pseudo";
	private static final String FIND_BY_PSEUDO = "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur FROM UTILISATEURS WHERE pseudo = :pseudo AND date_histo  IS NULL ORDER BY pseudo";

	private static final String COUNT_BY_NOM_PRENOM  = "SELECT COUNT(*) FROM UTILISATEURS WHERE nom = :nom AND prenom = :prenom AND date_histo  IS NULL" ;
    private static final String COUNT_BY_PSEUDO      = "SELECT COUNT(*) FROM UTILISATEURS WHERE pseudo = :pseudo AND date_histo IS NULL";
    private static final String COUNT_BY_MAIL        = "SELECT COUNT(*) FROM UTILISATEURS WHERE email = :email AND date_histo IS NULL";

    private static final String COUNT_BY_NOM_PRENOMMODIFIER  = "SELECT COUNT(*) FROM UTILISATEURS WHERE nom = :nom AND prenom = :prenom AND no_utilisateur != :noUtilisateur AND date_histo  IS NULL";
    private static final String COUNT_BY_PSEUDOMODIFIER      = "SELECT COUNT(*) FROM UTILISATEURS WHERE pseudo = :pseudo AND no_utilisateur != :noUtilisateur AND date_histo  IS NULL";
    private static final String COUNT_BY_NOUTILISATEUR       = "SELECT COUNT(*) FROM UTILISATEURS WHERE no_utilisateur = :noUtilisateur AND date_histo  IS NULL";
    private static final String COUNT_BY_MAILMODIFIER      	 = "SELECT COUNT(*) FROM UTILISATEURS WHERE email = :email AND no_utilisateur != :noUtilisateur AND date_histo IS NULL";

//    requete SQL avec histo
	private static final String FIND_ALL_HISTO = "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur, date_histo FROM UTILISATEURS ORDER BY date_histo ASC, pseudo";
	private static final String UPDATE_HISTO   = "UPDATE UTILISATEURS SET date_histo = GETDATE() WHERE no_utilisateur = :noUtilisateur";


 /**
 * constructeur de UtlisateurDAOimpl
	private static final String FIND_ALL = "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur FROM UTILISATEURS";
	private static final String FIND_BY_PSEUDO = "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur FROM UTILISATEURS WHERE pseudo = :pseudo";

	private static final String COUNT_BY_NOM_PRENOM  = "SELECT COUNT(*) FROM UTILISATEURS WHERE nom = :nom AND prenom = :prenom";
    private static final String COUNT_BY_PSEUDO      = "SELECT COUNT(*) FROM UTILISATEURS WHERE pseudo = :pseudo";

    private static final String COUNT_BY_NOM_PRENOMMODIFIER  = "SELECT COUNT(*) FROM UTILISATEURS WHERE nom = :nom AND prenom = :prenom AND no_utilisateur != :noUtilisateur";
    private static final String COUNT_BY_PSEUDOMODIFIER      = "SELECT COUNT(*) FROM UTILISATEURS WHERE pseudo = :pseudo AND no_utilisateur != :noUtilisateur";
    private static final String COUNT_BY_NOUTILISATEUR       = "SELECT COUNT(*) FROM UTILISATEURS WHERE no_utilisateur = :noUtilisateur";


    /**
 * constructeur de UtlisateurDAOimpl

 * @param jdbcTemplate
 */
public UtilisateurDAOimpl(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

/**
 * creation d'un utilisateur
 */
	@Override
	public void create(Utilisateur utilisateur) {

		MapSqlParameterSource mapParameterSource = new MapSqlParameterSource();
// ajout parametres pour la requete
		mapParameterSource.addValue("pseudo",utilisateur.getPseudo());
		mapParameterSource.addValue("nom",utilisateur.getNom());
		mapParameterSource.addValue("prenom",utilisateur.getPrenom());
		mapParameterSource.addValue("email",utilisateur.getEmail());
		mapParameterSource.addValue("telephone",utilisateur.getTelephone());
		mapParameterSource.addValue("rue",utilisateur.getRue());
		mapParameterSource.addValue("codePostal",utilisateur.getCodePostal());
		mapParameterSource.addValue("ville",utilisateur.getVille());
		mapParameterSource.addValue("motDePasse",utilisateur.getMotDePasse());
		mapParameterSource.addValue("credit",utilisateur.getCredit());
		mapParameterSource.addValue("administrateur",utilisateur.isAdministrateur());
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(CREATE, mapParameterSource, keyHolder);
		// recuperation de la clef pour la table utilisateur
		if (keyHolder != null && keyHolder.getKey() != null) {
			utilisateur.setNoUtilisateur(keyHolder.getKey().intValue());
		}
		utilisateur.setNoUtilisateur(keyHolder.getKey().intValue());
	}
/**
 *  lecture utilisateur
 */
	@Override
	public Utilisateur read(int noUtilisateur) {
		MapSqlParameterSource mapParameterSource = new MapSqlParameterSource();
// ajout parametre pour la requete
		mapParameterSource.addValue("noUtlisateur",noUtilisateur);
		return jdbcTemplate.queryForObject(READ, mapParameterSource,new UtilisateurRowMapper());
	}
/**
 *  mise à jour utilisateur sans admin et sans crédit
 */
	@Override
	public void update(Utilisateur utilisateur) {
		MapSqlParameterSource mapParameterSource = new MapSqlParameterSource();

// ajout parametre pour la requete
		mapParameterSource.addValue("noUtilisateur",utilisateur.getNoUtilisateur());
		mapParameterSource.addValue("pseudo",utilisateur.getPseudo());
		mapParameterSource.addValue("nom",utilisateur.getNom());
		mapParameterSource.addValue("prenom",utilisateur.getPrenom());
		mapParameterSource.addValue("email",utilisateur.getEmail());
		mapParameterSource.addValue("telephone",utilisateur.getTelephone());
		mapParameterSource.addValue("rue",utilisateur.getRue());
		mapParameterSource.addValue("codePostal",utilisateur.getCodePostal());
		mapParameterSource.addValue("ville",utilisateur.getVille());
		mapParameterSource.addValue("motDePasse",utilisateur.getMotDePasse());
		jdbcTemplate.update(UPDATE, mapParameterSource);
	}
	@Override
	public void updateCredit(int noUtilisateur, int credit) {
		MapSqlParameterSource mapParameterSource = new MapSqlParameterSource();
		mapParameterSource.addValue("noUtilisateur", noUtilisateur);
		mapParameterSource.addValue("credit", credit);
		jdbcTemplate.update(UPDATE_CREDIT, mapParameterSource);
	}
/**
 *  suppression d' utilisateur
 */
	@Override
	public void delete(int noUtlisateur) {
		MapSqlParameterSource mapParameterSource = new MapSqlParameterSource();
// ajout parametre pour la requete
		mapParameterSource.addValue("noUtlisateur",noUtlisateur);

		jdbcTemplate.update(DELETE, mapParameterSource);

	}
/**
 *  liste de tous les utilisateurs
 */
	@Override
	public List<Utilisateur> findAll() {
		return jdbcTemplate.query(FIND_ALL, new UtilisateurRowMapper());
	}

/**
 *  controle avec un count des doublons nom/prenom en creation
 */
	@Override
	public int countByNomPrenom(String nom, String prenom) {
		MapSqlParameterSource mapParameterSource = new MapSqlParameterSource();
// ajout parametres pour la requete
		mapParameterSource.addValue("nom",nom);
		mapParameterSource.addValue("prenom",prenom);

		return jdbcTemplate.queryForObject(COUNT_BY_NOM_PRENOM , mapParameterSource, Integer.class) ;
	}
/**
 * controle avec un count des doublons pseudo en creation
 */
	@Override
	public int countByPseudo(String pseudo) {
		MapSqlParameterSource mapParameterSource = new MapSqlParameterSource();
// ajout parametre pour la requete
		mapParameterSource.addValue("pseudo",pseudo);

		return jdbcTemplate.queryForObject(COUNT_BY_PSEUDO , mapParameterSource, Integer.class) ;
	}

/**
 * controle avec un count des doublons mail en creation
 */
	@Override
	public int countByMail(String email) {
		MapSqlParameterSource mapParameterSource = new MapSqlParameterSource();
		// ajout parametre pour la requete
		mapParameterSource.addValue("email",email);

		return jdbcTemplate.queryForObject(COUNT_BY_MAIL , mapParameterSource, Integer.class) ;
	}

/**
 * controle avec un count des doublons mail en modification
 */
	@Override
	public int countByMailModifier(int noUtilisateur, String email) {
		MapSqlParameterSource mapParameterSource = new MapSqlParameterSource();
		// ajout parametre pour la requete
		mapParameterSource.addValue("noUtilisateur",noUtilisateur);
		mapParameterSource.addValue("email",email);

		return jdbcTemplate.queryForObject(COUNT_BY_MAILMODIFIER, mapParameterSource, Integer.class);
	}


/**
 * controle avec un count des doublons nom/prenom en modification
 */
	@Override
	public int countByNomPrenomModifier(int noUtilisateur, String nom, String prenom) {
		MapSqlParameterSource mapParameterSource = new MapSqlParameterSource();
// ajout parametre pour la requete
		mapParameterSource.addValue("noUtilisateur",noUtilisateur);
		mapParameterSource.addValue("nom",nom);
		mapParameterSource.addValue("prenom",prenom);

		return jdbcTemplate.queryForObject(COUNT_BY_NOM_PRENOMMODIFIER , mapParameterSource, Integer.class) ;
	}

/**
 * 	controle avec un count des doublons pseudo en modification
 */
	@Override
	public int countByPseudoModifier(int noUtilisateur, String pseudo) {
		MapSqlParameterSource mapParameterSource = new MapSqlParameterSource();
// ajout parametre pour la requete
		mapParameterSource.addValue("noUtilisateur",noUtilisateur);
		mapParameterSource.addValue("pseudo",pseudo);

		return jdbcTemplate.queryForObject(COUNT_BY_PSEUDOMODIFIER , mapParameterSource, Integer.class);
	}

@Override
	public Utilisateur findByPseudo(String pseudo) {
		MapSqlParameterSource mapParameterSource = new MapSqlParameterSource();
// ajout parametre pour la requete
		mapParameterSource.addValue("pseudo",pseudo);

		return jdbcTemplate.queryForObject(FIND_BY_PSEUDO, mapParameterSource, new UtilisateurRowMapper());
}

/**
 *  comptage par no utlisateur
 */
@Override
public int countByNoUtilisateur(int noUtilisateur) {
	MapSqlParameterSource mapParameterSource = new MapSqlParameterSource();
	// ajout parametre pour la requete
			mapParameterSource.addValue("noUtilisateur",noUtilisateur);


			return jdbcTemplate.queryForObject(COUNT_BY_NOUTILISATEUR , mapParameterSource, Integer.class) ;
}
/**
 * methode permettant de permettant lister tous les utlisateurs meme les historisés
 */
@Override
public List<Utilisateur> findAllHisto() {
	// TODO Auto-generated method stub
	return  jdbcTemplate.query(FIND_ALL_HISTO ,new BeanPropertyRowMapper<>(Utilisateur.class));
}

/**
 *  methode permettant de mettre à jour la date d' histo
 */
@Override
public void updateHisto(int noUtilisateur) {
	MapSqlParameterSource mapParameterSource = new MapSqlParameterSource();
// ajout parametres de requete
	mapParameterSource.addValue("noUtilisateur",noUtilisateur);
	jdbcTemplate.update(UPDATE_HISTO, mapParameterSource);

}


public class UtilisateurRowMapper implements RowMapper<Utilisateur> {

	@Override
	public Utilisateur mapRow(ResultSet rs, int rowNum) throws SQLException {
		Utilisateur utilisateur = new Utilisateur();

		utilisateur.setNoUtilisateur(rs.getInt("no_utilisateur"));
		utilisateur.setPseudo(rs.getString("pseudo"));
		utilisateur.setNom(rs.getString("nom"));
		utilisateur.setPrenom(rs.getString("prenom"));
		utilisateur.setEmail(rs.getString("email"));
		utilisateur.setTelephone(rs.getString("telephone"));
		utilisateur.setRue(rs.getString("rue"));
		utilisateur.setCodePostal(rs.getString("code_postal"));
		utilisateur.setVille(rs.getString("ville"));
		utilisateur.setMotDePasse(rs.getString("mot_de_passe"));
		utilisateur.setCredit(rs.getInt("credit"));
		utilisateur.setAdministrateur(rs.getBoolean("administrateur"));

		return utilisateur;
	}
}

}
