package com.example.encheres.bll;

import com.example.encheres.bo.Retrait;

public interface RetraitService {
	void creerRetrait (Retrait retrait);
	Retrait lectureRetrait (int noArticle);

}
