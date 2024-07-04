package com.example.encheres.bll;

import com.example.encheres.bo.Retrait;

public interface RetraitService {
	void create(Retrait retrait);
	Retrait read(int noArticle);
	void update(Retrait retrait);
	void delete ( int noArticle);
}
