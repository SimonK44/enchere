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
	public void delete(int noCategorie) {
		this.categorieDAO.delete(noCategorie);
	}

	@Override
	public void update(int categorie) {

	}

	@Override
	public void updateDateSuppression(int noCategorie) {
		this.categorieDAO.updateDateSuppression(noCategorie);
	}

	@Override
	public List<Categorie> findAll() {
		return this.categorieDAO.findAll();
	}
	@Override
	public List<Categorie> findAllAdmin() {
		return this.categorieDAO.findAllAdmin();
	}
}
