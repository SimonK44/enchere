package com.example.encheres.dal;

import java.util.List;

import com.example.encheres.bo.Categorie;

public interface CategorieDAO {
	void create ( Categorie categorie);
	Categorie read (int noCategorie);
	void update(Categorie categorie);
	void updateDateSuppression(int noCategorie);
	void delete(int noCategorie);
	List<Categorie> findAll();
	List<Categorie> findAllAdmin();
}
