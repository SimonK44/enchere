package com.example.encheres.dal;

import java.util.List;
import java.util.Optional;

import com.example.encheres.bo.Enchere;

public interface EnchereDAO {

	void create (Enchere enchere);
	Enchere read(int noUtilisateur, int noArticle);
	void update (Enchere enchere );
	void delete(int noUtilisateur, int noArticle);
	List<Enchere> findByUtilisateur(int noUtilisateur);
	List<Enchere> findByArticle(int noArticle);
	Optional<Enchere> montantMax(int noArticle);


}
