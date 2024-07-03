package com.example.encheres.bll;

import com.example.encheres.bo.ArticleVendu;
import com.example.encheres.bo.Categorie;
import com.example.encheres.dal.CategorieDAO;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CategorieImpl implements CategorieService {

	private CategorieDAO categorieDAO;

	public CategorieImpl(CategorieDAO categorieDAO) {
		this.categorieDAO = categorieDAO;
	}


	@Override
	public void create(Categorie categorie) {

	}

	@Override
	public Categorie read(int noCategorie) {
		return categorieDAO.read(noCategorie);
	}

	@Override
	public void delete(int categorie) {

	}

	@Override
	public void update(int categorie) {

	}

	@Override
	public List<Categorie> findAll() {
		return this.categorieDAO.findAll();
	}
}
