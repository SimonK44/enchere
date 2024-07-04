package com.example.encheres.dal;

import com.example.encheres.bo.Retrait;

public interface RetraitDAO {

	void create(Retrait retrait);
	Retrait read(int noArticle);
	void update(Retrait retrait);
	void delete ( int noArticle);
	
	
}
