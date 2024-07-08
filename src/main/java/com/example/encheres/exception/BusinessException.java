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
