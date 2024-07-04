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
	private UtilisateurDAO utilisateurDAO;
	private RetraitDAO retraitDAO;



	public ArticleVenduImpl(ArticleVenduDAO articleVenduDAO, UtilisateurDAO utilisateurDAO, RetraitDAO retraitDAO) {
		super();
		this.articleVenduDAO = articleVenduDAO;
		this.utilisateurDAO = utilisateurDAO;
		this.retraitDAO = retraitDAO;
	}

	@Override
	public void create(ArticleVendu articleVendu) {

	}

	@Override
	public ArticleVendu lectureArticleVendu(int noArticleVendu) {
		return this.articleVenduDAO.read(noArticleVendu);
	}

	@Override
	public List<ArticleVendu> findAll() {

    	List<ArticleVendu> articles = articleVenduDAO.findAll();

    	System.out.println("articles : " + articles);
    	for(ArticleVendu a : articles ) {
    		a.setVendeur(utilisateurDAO.read(a.getVendeur().getNoUtilisateur()));
    		System.out.println("articlevenduimpl : " + a);
    	}

    	//ArticleVendu [noArticle=1, nomArticle=Tan, description=une e,
    	//dateDebutEnchere=2024-07-01, dateFinEnchere=2024-07-30, prixInitial=100.0, prixVente=150.0,
    	//categorie=Categorie [noCategorie=1, libelle=null], acheteur=Utilisateur [noUtilisateur=2, pseudo=null, nom=null, prenom=null, email=null, telephone=null, rue=null, codePostal=null, ville=null, motDePasse=null, credit=0, administrateur=false],
    	//vendeur=Utilisateur [noUtilisateur=1, pseudo=null, nom=null, prenom=null, email=null, telephone=null, rue=null, codePostal=null, ville=null, motDePasse=null, credit=0, administrateur=false], encheres=[]],

    	System.out.println(utilisateurDAO.read(1));



//		articles.foreach(u->u.setUtilisateur(u))
//    	Utilisateur u -> u.s(this.utilisateurService.lectureUtilisateur();))


//    	 articles.forEach(u -> {
//    	 u.setVendeur(utilisateurDAO.read(u.getVendeur().getNoUtilisateur()));
//    	});


		return articles;

	}

	@Override
	public void modifierArticleVendu(ArticleVendu articleVendu) {

	}

	@Override
	public void supprimerArticleVendu(int articleVendu) {

	}

	@Override
	@Transactional
	public void createArticleWithRetrait(ArticleVendu articleVendu, Retrait adresse, Utilisateur user) {
		Utilisateur acheteur = new Utilisateur();
		Utilisateur vendeur = new Utilisateur();
		vendeur.setNoUtilisateur(user.getNoUtilisateur());
		articleVendu.setAcheteur(acheteur);
		articleVendu.setVendeur(vendeur);
		this.articleVenduDAO.create(articleVendu);
		adresse.setNoArticle(articleVendu.getNoArticle());
		this.retraitDAO.create(adresse);
	}
}
