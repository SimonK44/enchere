package com.example.encheres.bll;

import com.example.encheres.bo.ArticleVendu;
import com.example.encheres.bo.Categorie;
import com.example.encheres.dal.CategorieDAO;
import com.example.encheres.exception.BusinessException;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	@Transactional(rollbackFor = BusinessException.class)
	public void delete(int noCategorie) throws BusinessException {
		BusinessException be = new BusinessException() ;
		
		try {
			this.categorieDAO.delete(noCategorie);
		} catch (DataAccessException e) {
			System.out.println("utlisateur service pb Creation");
			be.addError(BusinessException.ERREUR_0);
			throw be;
		}	
	}

	@Override
	@Transactional(rollbackFor = BusinessException.class)
	public void update(int categorie) throws BusinessException  {
//		BusinessException be = new BusinessException() ;
	}

	@Override
	@Transactional(rollbackFor = BusinessException.class)
	public void updateDateSuppression(int noCategorie) throws BusinessException  {
		BusinessException be = new BusinessException() ;
		
		try {
			this.categorieDAO.updateDateSuppression(noCategorie);
		} catch (DataAccessException e) {
			System.out.println("utlisateur service pb Creation");
			be.addError(BusinessException.ERREUR_0);
			throw be;
		}	
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
