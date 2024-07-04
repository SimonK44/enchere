package com.example.encheres.bll;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.encheres.bo.Retrait;
import com.example.encheres.dal.RetraitDAO;

public class RetraitServiceImpl implements RetraitService {
	@Autowired
	private RetraitDAO retraitDAO;

	@Override
	public void creerRetrait(Retrait retrait) {
		retraitDAO.create(retrait);
		
	}

	@Override
	public Retrait lectureRetrait(int noArticle) {
		return retraitDAO.read(noArticle);
		
	}
	




}
