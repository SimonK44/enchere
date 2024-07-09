package com.example.encheres.bll;

import com.example.encheres.bo.Retrait;
import com.example.encheres.dal.RetraitDAO;
import com.example.encheres.exception.BusinessException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RetraitServiceImpl implements RetraitService {
	private RetraitDAO retraitDao;
	Logger logger =LoggerFactory.getLogger(RetraitServiceImpl.class);

	public RetraitServiceImpl(RetraitDAO retraitDao) {
		this.retraitDao = retraitDao;
	}

	@Override
	@Transactional(rollbackFor = BusinessException.class)
	public void create(Retrait retrait) throws BusinessException  {
		BusinessException be = new BusinessException() ;
		try {
			this.retraitDao.create(retrait);
		} catch (DataAccessException e) {			
			this.logger.error("Probleme creation retrait" + retrait.getNoArticle() );		
			be.addError(BusinessException.ERREUR_0);
			throw be;
		}
	}

	@Override
	public Retrait read(int noArticle) {
		return this.retraitDao.read(noArticle);
	}
}
