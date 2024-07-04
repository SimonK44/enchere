package com.example.encheres.bll;

import com.example.encheres.bo.Retrait;

public interface RetraitService {
	void create(Retrait retrait);
	Retrait read(int noArticle);
}
