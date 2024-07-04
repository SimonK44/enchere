package com.example.encheres.bll;

import com.example.encheres.bo.ArticleVendu;
import com.example.encheres.bo.Retrait;
import com.example.encheres.bo.Utilisateur;
import com.example.encheres.dal.ArticleVenduDAO;
import com.example.encheres.dal.RetraitDAO;
import com.example.encheres.dal.UtilisateurDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class ArticleVenduImpl implements ArticleVenduService {

	private ArticleVenduDAO articleVenduDAO;
	private RetraitDAO retraitDAO;

	public ArticleVenduImpl(ArticleVenduDAO articleVenduDAO, RetraitDAO retraitDAO) {
		this.articleVenduDAO = articleVenduDAO;
		this.retraitDAO = retraitDAO;
	}

	@Override
	public void create(ArticleVendu articleVendu) {
		//TODO initialisation du vendeur  -> Recuperer l'id de l'utilisateur
		Utilisateur acheteur = new Utilisateur();
		Utilisateur vendeur = new Utilisateur();

		vendeur.setNoUtilisateur(1);

		articleVendu.setAcheteur(acheteur);
		articleVendu.setVendeur(vendeur);

		this.articleVenduDAO.create(articleVendu);
	}

	@Override
	public ArticleVendu lectureArticleVendu(int noArticleVendu) {
		return this.articleVenduDAO.read(noArticleVendu);
	}

	@Override
	public List<ArticleVendu> findAll() {
		return this.articleVenduDAO.findAll();
	}

	@Override
	public void modifierArticleVendu(ArticleVendu articleVendu) {

	}

	@Override
	public void supprimerArticleVendu(int articleVendu) {

	}

	@Override
	@Transactional
	public void createArticleWithRetrait(ArticleVendu articleVendu, Retrait adresse) {
		this.create(articleVendu);
		adresse.setNoArticle(articleVendu.getNoArticle());
		this.retraitDAO.create(adresse);
	}
}
