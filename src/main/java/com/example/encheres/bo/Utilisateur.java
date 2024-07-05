package com.example.encheres.bo;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/*
 * classe decrivant un utilisateur
 */
public class Utilisateur {

	/*
	 * En tant qu’utilisateur, je peux m’inscrire sur la plateforme Enchères.org. 
	 * Le pseudo doit être unique sur toute la plateforme, 
	 * ainsi que l’email. 
	 * Le pseudo n’accepte que des caractères alphanumériques. 
	 * Si la création du profil est validée, 
	 * l’utilisateur est dirigé vers la page d’accueil 
	 */	
	
	private int noUtilisateur;
	@NotBlank
	@Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Le pseudo doit être alphanumérique")
	private String pseudo;
	@NotBlank
	private String nom;
	@NotBlank
	private String prenom;
	@NotBlank
	@Email
	private String email;
	@Digits(fraction = 0, integer = 10, message = "Le téléphone doit comporter 10 chiffres")
	private String telephone;
	@NotBlank
	private String rue;
	@NotBlank
	@Digits(fraction = 0, integer = 10, message = "Le téléphone doit comporter 5 chiffres")
	private String codePostal;
	@NotBlank
	private String ville;
	@NotBlank
	@Size(min = 8)
	private String motDePasse;
//	@Transient
//    private String confirmPassword;    
//    @AssertTrue(message = "Les mots de passe ne correspondent pas")
//    private boolean isPasswordConfirmed() {
//        return motDePasse != null && motDePasse.equals(confirmPassword);
//    }
	private int credit;
	private boolean administrateur;
	
	
/**
 *  constructeur sans attribut	
 */
public Utilisateur() {
		
	}

/**
 * constructeur de la classe Utilisateur	
 * @param noUtilisateur
 * @param pseudo
 * @param nom
 * @param prenom
 * @param email
 * @param telephone
 * @param rue
 * @param codePostal
 * @param ville
 * @param motDePasse
 * @param credit
 * @param administrateur
 */
	public Utilisateur(int noUtilisateur, String pseudo, String nom, String prenom, String email, String telephone, String rue,
			String codePostal, String ville, String motDePasse, int credit, boolean administrateur) {
		
		this.noUtilisateur = noUtilisateur;
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.motDePasse = motDePasse;
		this.credit = credit;
		this.administrateur = administrateur;
	}

public int getNoUtilisateur() {
	return noUtilisateur;
}

public void setNoUtilisateur(int noUtilisateur) {
	this.noUtilisateur = noUtilisateur;
}

public String getPseudo() {
	return pseudo;
}

public void setPseudo(String pseudo) {
	this.pseudo = pseudo;
}

public String getNom() {
	return nom;
}

public void setNom(String nom) {
	this.nom = nom;
}

public String getPrenom() {
	return prenom;
}

public void setPrenom(String prenom) {
	this.prenom = prenom;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getTelephone() {
	return telephone;
}

public void setTelephone(String telephone) {
	this.telephone = telephone;
}

public String getRue() {
	return rue;
}

public void setRue(String rue) {
	this.rue = rue;
}

public String getCodePostal() {
	return codePostal;
}

public void setCodePostal(String codePostal) {
	this.codePostal = codePostal;
}

public String getVille() {
	return ville;
}

public void setVille(String ville) {
	this.ville = ville;
}

public String getMotDePasse() {
	return motDePasse;
}

public void setMotDePasse(String motDePasse) {
	this.motDePasse = motDePasse;
}

public int getCredit() {
	return credit;
}

public void setCredit(int credit) {
	this.credit = credit;
}

public boolean isAdministrateur() {
	return administrateur;
}

public void setAdministrateur(boolean administrateur) {
	this.administrateur = administrateur;
}

@Override
public String toString() {
	return "Utilisateur [noUtilisateur=" + noUtilisateur + ", pseudo=" + pseudo + ", nom=" + nom + ", prenom=" + prenom
			+ ", email=" + email + ", telephone=" + telephone + ", rue=" + rue + ", codePostal=" + codePostal
			+ ", ville=" + ville + ", motDePasse=" + motDePasse + ", credit=" + credit + ", administrateur="
			+ administrateur + "]";
}

	
	
}
