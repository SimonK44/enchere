package com.example.encheres.exception;



import java.util.ArrayList;
import java.util.List;

public class BusinessException extends Exception {
	
	private static final long serialVersionUID = 1L;
	private List<String> erreurs;
	
	public static final String ERREUR_0 = "Un probleme est survenu lors de la connexion à la table";
	public static final String ERREUR_1 = "Nom/prenom deja existants";
	public static final String ERREUR_2 = "Pseudo deja existant";
	public static final String ERREUR_3 = "Article inexistant";
	public static final String ERREUR_4 = "Utilisateur inexistant";
	public static final String ERREUR_5 = "Montant enchere trop basse";
	public static final String ERREUR_6 = "Les mots de passe sont différents";
	public static final String ERREUR_7 = "Le mail est deja existant";
	public static final String ERREUR_8 = "Le mot de passe actuel est incorrect";
	public static final String ERREUR_9 = "La saisie du mot de passe est obligatoire";
	
	public static final String LOGGER_0  = "Réussite suppression categorie : ";
	public static final String LOGGER_1  = "Probleme suppression categorie : ";
	public static final String LOGGER_2  = "Réussite MAJ categorie : ";
	public static final String LOGGER_3  = "Probleme MAJ categorie : ";
	public static final String LOGGER_4  = "Réussite téléchargement fichier : ";
	public static final String LOGGER_5  = "Probleme téléchargement fichier : ";
	public static final String LOGGER_6  = "Réussite creation utilisateur : ";
	public static final String LOGGER_7  = "Probleme creation utilisateur : "; 
	public static final String LOGGER_8  = "Réussite MAJ utilisateur : ";
	public static final String LOGGER_9  = "Probleme MAJ utilisateur : "; 
	public static final String LOGGER_10 = "Réussite suppression utilisateur : ";
	public static final String LOGGER_11 = "Probleme suppression utilisateur : "; 
	public static final String LOGGER_12 = "Réussite historisation utilisateur : ";
	public static final String LOGGER_13 = "Probleme historisation utilisateur : "; 

	public BusinessException() {
		super();
		erreurs = new ArrayList<String>();
	}

	
	public void addError(String message) {
		erreurs.add(message);
	}

	public boolean hasError () {
		return !erreurs.isEmpty();
	}


	public List<String> getErreurs() {
		return erreurs;
	}
	

}
