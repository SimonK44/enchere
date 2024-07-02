package eni.projet.enchere.dal;

import java.util.List;

import eni.projet.enchere.bo.Categorie;

public interface CategorieDAO {

	void create ( Categorie categorie);
	Categorie read (int noCategorie);
	void update(Categorie categorie);
	void delete(int noCategorie);
	List<Categorie> findAll();
	
}
