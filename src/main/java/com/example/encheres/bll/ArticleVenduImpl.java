package com.example.encheres.bll;

import com.example.encheres.bo.ArticleVendu;
import com.example.encheres.bo.Enchere;
import com.example.encheres.bo.Retrait;
import com.example.encheres.bo.Utilisateur;
import com.example.encheres.dal.ArticleVenduDAO;
import com.example.encheres.dal.EnchereDAO;
import com.example.encheres.dal.RetraitDAO;
import com.example.encheres.dal.ArticleVenduDynamiqueDAO;
import com.example.encheres.dal.UtilisateurDAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


@Service
public class ArticleVenduImpl implements ArticleVenduService {

	private ArticleVenduDAO articleVenduDAO;
	private UtilisateurDAO utilisateurDAO;
	private RetraitDAO retraitDAO;
	private EnchereDAO enchereDAO;


	@Value("${upload.path}")
	private String uploadPath;


	public ArticleVenduImpl(
			ArticleVenduDAO articleVenduDAO,
			UtilisateurDAO utilisateurDAO,
			RetraitDAO retraitDAO,
			EnchereDAO enchereDAO
	) {
		super();
		this.articleVenduDAO = articleVenduDAO;
		this.utilisateurDAO = utilisateurDAO;
		this.retraitDAO = retraitDAO;
		this.enchereDAO = enchereDAO;
	}
	private ArticleVenduDynamiqueDAO articleVenduDynamiqueDAO;



	@Override
	public void create(ArticleVendu articleVendu) {

	}

	@Override
	public ArticleVendu lectureArticleVendu(int noArticleVendu) {
		return this.articleVenduDAO.read(noArticleVendu);
	}

	@Override
	@Transactional
	public List<ArticleVendu> findAll() {

    	List<ArticleVendu> articles = articleVenduDAO.findAll();

    	for(ArticleVendu a : articles ) {
    		a.setVendeur(utilisateurDAO.read(a.getVendeur().getNoUtilisateur()));
    	}

    	//ArticleVendu [noArticle=1, nomArticle=Tan, description=une e,
    	//dateDebutEnchere=2024-07-01, dateFinEnchere=2024-07-30, prixInitial=100.0, prixVente=150.0,
    	//categorie=Categorie [noCategorie=1, libelle=null], acheteur=Utilisateur [noUtilisateur=2, pseudo=null, nom=null, prenom=null, email=null, telephone=null, rue=null, codePostal=null, ville=null, motDePasse=null, credit=0, administrateur=false],
    	//vendeur=Utilisateur [noUtilisateur=1, pseudo=null, nom=null, prenom=null, email=null, telephone=null, rue=null, codePostal=null, ville=null, motDePasse=null, credit=0, administrateur=false], encheres=[]],

    	System.out.println(utilisateurDAO.read(7));
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
	public void modifierArticleVenduPrixVente(int noArticleVendu, int prixVente) {
		this.articleVenduDAO.updatePrixVente(noArticleVendu, prixVente);
	}

	@Override
	public void supprimerArticleVendu(int articleVendu) {

	}

	@Override
	@Transactional
	public void createArticleWithRetrait(ArticleVendu articleVendu, Retrait adresse, Utilisateur user, MultipartFile image) {
		Utilisateur acheteur = new Utilisateur();
		Utilisateur vendeur = new Utilisateur();
		vendeur.setNoUtilisateur(user.getNoUtilisateur());
		articleVendu.setAcheteur(acheteur);
		articleVendu.setVendeur(vendeur);
		this.articleVenduDAO.create(articleVendu);
		adresse.setNoArticle(articleVendu.getNoArticle());
		this.retraitDAO.create(adresse);

		if (!image.isEmpty()) {
			try {
				byte[] bytes = image.getBytes();
				Path path = Paths.get(uploadPath + articleVendu.getNoArticle() + ".jpg");
				Files.write(path, bytes);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Transactional
	public void encherirArticle(int noArticleVendu, int proposition, Utilisateur user) {
		Utilisateur utilisateur = this.utilisateurDAO.read(user.getNoUtilisateur());
		// recuperer derniere offre
		Enchere lastEnchereMax = this.enchereDAO.montantMax(noArticleVendu);
		//rendre les points
		System.out.println("AVANT dernierAcheteur\n");
		Utilisateur dernierAcheteur = this.utilisateurDAO.read(lastEnchereMax.getUtilisateur().getNoUtilisateur());
		System.out.println("APRES dernierAcheteur\n");

		System.out.println("\n CRvz NW : " + (dernierAcheteur.getCredit() + lastEnchereMax.getMontantEnchere()));
		this.utilisateurDAO.updateCredit(
				lastEnchereMax.getUtilisateur().getNoUtilisateur(),
				dernierAcheteur.getCredit() + lastEnchereMax.getMontantEnchere()
		);
		// retirer credit utilisateur
		this.utilisateurDAO.updateCredit(
				utilisateur.getNoUtilisateur(),
				utilisateur.getCredit() - proposition
		);
		//ajouter enchere
		Enchere nouvelleEnchere = new Enchere();
		ArticleVendu articleVenduEnchere = new ArticleVendu();
		articleVenduEnchere.setNoArticle(noArticleVendu);
		nouvelleEnchere.setMontantEnchere(proposition);
		nouvelleEnchere.setArticleVendu(articleVenduEnchere);
		nouvelleEnchere.setUtilisateur(user);
		this.enchereDAO.create(nouvelleEnchere);
		//modifier articlevendu
		this.articleVenduDAO.updatePrixVente(noArticleVendu, proposition);

	}

	public List<ArticleVendu> findAllComplexe(int requete,  String nomArticle, int noCategorie, int noUtilisateurVendeur, int noUtilisateurAcheteur) {

		// à developper


		return null;
	}

}
