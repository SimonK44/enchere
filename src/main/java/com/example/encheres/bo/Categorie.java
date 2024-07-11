package com.example.encheres.bo;

import java.time.LocalDate;

public class Categorie {
	private int noCategorie;
	private String libelle;
	private LocalDate dateSuppression;
	/* constructeur de la class Categorie */
	public Categorie(int noCategorie, String libelle, LocalDate dateSuppression) {
		this.noCategorie = noCategorie;
		this.libelle = libelle;
		this.dateSuppression = dateSuppression;
	}
	public Categorie() {
	}
	public int getNoCategorie() {
		return noCategorie;
	}
	public void setNoCategorie(int noCategorie) {
		this.noCategorie = noCategorie;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public LocalDate getDateSuppression() {
		return dateSuppression;
	}
	public void setDateSuppression(LocalDate dateSuppression) {
		this.dateSuppression = dateSuppression;
	}
	@Override
	public String toString() {
		return "Categorie [" +
				"noCategorie=" + noCategorie +
				", libelle=" + libelle +
				", dateSuppression=" + dateSuppression +
				"]";
	}
}
