package com.example.encheres.bo;

import java.time.LocalDate;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/*
 * classe decrivant un utilisateur
 */
public class Utilisateur {
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
	@NotBlank
	@Size(min=10)
	@Digits(fraction = 0, integer = 10, message = "Le téléphone doit être compose de chiffres uniquement")
	private String telephone;
	@NotBlank
	private String rue;
	@NotBlank
	@Digits(fraction = 0, integer = 10, message = "Le code postal doit être compose de chiffres uniquement")
	@Size(min=5)
	private String codePostal;
	@NotBlank
	private String ville;
	@NotBlank
	@Size(min = 8)
	private String motDePasse;
	@NotBlank
	private String confirmMotDePasse;
	private int credit;
	private boolean administrateur;
	private LocalDate dateHisto;

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
	public Utilisateur(
			int noUtilisateur,
			String pseudo,
			String nom,
			String prenom,
			String email,
			String telephone,
			String rue,
			String codePostal,
			String ville,
			String motDePasse,
			int credit,
			boolean administrateur
	) {
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


	public LocalDate getDateHisto() {
		return dateHisto;
	}

	public void setDateHisto(LocalDate dateHisto) {
		this.dateHisto = dateHisto;
	}

	public String getConfirmMotDePasse() {
		return confirmMotDePasse;
	}

	public void setConfirmMotDePasse(String confirmMotDePasse) {
		this.confirmMotDePasse = confirmMotDePasse;
	}

	@Override
	public String toString() {
		return "Utilisateur [noUtilisateur=" + noUtilisateur + ", pseudo=" + pseudo + ", nom=" + nom + ", prenom=" + prenom
				+ ", email=" + email + ", telephone=" + telephone + ", rue=" + rue + ", codePostal=" + codePostal
				+ ", ville=" + ville + ", motDePasse=" + motDePasse + ", confmotDePasse=" + confirmMotDePasse + ", credit=" + credit + ", administrateur="
				+ administrateur + ", dateHisto=" + dateHisto + "]";
	}
}
