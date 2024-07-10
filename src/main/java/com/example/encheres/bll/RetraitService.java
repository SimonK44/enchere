package com.example.encheres.bll;

import com.example.encheres.bo.Retrait;
import com.example.encheres.exception.BusinessException;

public interface RetraitService {
	void create(Retrait retrait) throws BusinessException;
	void update (Retrait retrait);
	Retrait read(int noArticle);
}
