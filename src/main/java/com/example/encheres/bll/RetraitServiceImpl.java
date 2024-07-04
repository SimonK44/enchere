package com.example.encheres.bll;

import com.example.encheres.bo.Retrait;
import com.example.encheres.dal.RetraitDAO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RetraitServiceImpl implements RetraitService {
	private RetraitDAO retraitDao;

	public RetraitServiceImpl(RetraitDAO retraitDao) {
		this.retraitDao = retraitDao;
	}

	@Override
	public void create(Retrait retrait) {
		this.retraitDao.create(retrait);
	}

	@Override
	public Retrait read(int noArticle) {
		return this.retraitDao.read(noArticle);
	}
}
