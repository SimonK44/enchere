package com.example.encheres.bll;

import com.example.encheres.bo.Enchere;
import com.example.encheres.bo.Retrait;
import com.example.encheres.dal.RetraitDao;
import com.example.encheres.exception.BusinessException;

import java.util.List;

public interface RetraitService {
	void create(Retrait retrait);
	Retrait read(int noArticle);
	void update(Retrait retrait);
	void delete ( int noArticle);
}
