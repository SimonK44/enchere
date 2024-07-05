package com.example.encheres.bll;


import com.example.encheres.bo.ArticleVendu;
import com.example.encheres.bo.Categorie;

import java.util.List;

public interface CategorieService {
	void create ( Categorie categorie);
	Categorie read(int noCategorie);
	void delete(int categorie);

	void update(int categorie);
	void updateDateSuppression(int noCategorie);
	List<Categorie> findAll();
	List<Categorie> findAllAdmin();
}
