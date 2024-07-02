package eni.projet.enchere.dal;

import java.util.List;

import eni.projet.enchere.bo.Retrait;

public interface RetraitDao {

	void create(Retrait retrait);
	Retrait read(int noArticle);
	void update(Retrait retrait);
	void delete ( int noArticle);
	
	
}
