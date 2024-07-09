package com.example.encheres;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.encheres.bo.ArticleVendu;
import com.example.encheres.dal.ArticleVenduDynamiqueDAO;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
public class ArticleVenduDynamiqueTest {
	protected final Log logger = LogFactory.getLog(getClass());
	
	private String transactionType;
	private int requete;
	private String nomArticle;
	private int noCategorie;
	private int noUtilisateurVendeur;
	private int noUtilisateurAcheteur;
	List<ArticleVendu> a;
	
	
	
	@Autowired
	private ArticleVenduDynamiqueDAO articleVenduDynamiqueDAO;
	
	
	@Test
	void test01_enchereOuverte() {
// test encheres ouvertes
		System.out.println("---------------- debut test achat 1");
		transactionType = "achat";
		requete = 1;
		nomArticle = null;
		noUtilisateurVendeur = 8;
		noUtilisateurAcheteur = 7;
		
		try {
			a = articleVenduDynamiqueDAO.findDynamique(transactionType, requete, nomArticle,noCategorie,noUtilisateurVendeur,noUtilisateurAcheteur );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		System.out.println("test 1 resultat : " + a);
		assertNotNull(a);
		logger.info("Test 1 : encheres ouvertes");
		logger.info(a);	
	
	}

	@Test
	void test02_enchereEnCours() {
		// test encheres en cours
				System.out.println("---------------- debut test achat 2");
				transactionType = "achat";
				requete = 2;
				nomArticle = null;
				noUtilisateurVendeur = 8;
				noUtilisateurAcheteur = 7;
				
				try {
					a = articleVenduDynamiqueDAO.findDynamique(transactionType, requete, nomArticle,noCategorie,noUtilisateurVendeur,noUtilisateurAcheteur );
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    
				System.out.println("test 2 resultat : " + a);
				assertNotNull(a);
				logger.info("Test 2 : encheres en cours");
				logger.info(a);	
			
			}	
	
	@Test
	void test03_encheresOuvertes_encheresEnCours() {
		// test encheres en cours
		System.out.println("---------------- debut test achat 3");
				transactionType = "achat";
				requete = 3;
				nomArticle = null;
				noUtilisateurVendeur = 8;
				noUtilisateurAcheteur = 7;
				
				try {
					a = articleVenduDynamiqueDAO.findDynamique(transactionType, requete, nomArticle,noCategorie,noUtilisateurVendeur,noUtilisateurAcheteur );
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    
				System.out.println("test 3 resultat : " + a);
				assertNotNull(a);
				logger.info("Test 3 : encheres ouvertes et encheres en cours ");
				logger.info(a);	
			
			}	
	
	@Test
	void test04_mes_encheres_remportees() {
		System.out.println("---------------- debut test achat 4");
		// test encheres en cours
				transactionType = "achat";
				requete = 4;
				nomArticle = null;
				noUtilisateurVendeur = 8;
				noUtilisateurAcheteur = 7;
				
				try {
					a = articleVenduDynamiqueDAO.findDynamique(transactionType, requete, nomArticle,noCategorie,noUtilisateurVendeur,noUtilisateurAcheteur );
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    
				System.out.println("test 4 resultat : " + a);
				assertNotNull(a);
				logger.info("Test 4 : mes encheres remportées ");
				logger.info(a);	
			
			}	

	@Test
	void test05_mes_encheres_ouvertes_mes_encheres_remportees() {
		System.out.println("---------------- debut test achat 5");
		// test encheres en cours
				transactionType = "achat";
				requete = 5;
				nomArticle = null;
				noUtilisateurVendeur = 8;
				noUtilisateurAcheteur = 7;
				
				try {
					a = articleVenduDynamiqueDAO.findDynamique(transactionType, requete, nomArticle,noCategorie,noUtilisateurVendeur,noUtilisateurAcheteur );
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    
				System.out.println("test 5 resultat : " + a);
				assertNotNull(a);
				logger.info("Test 5 : mes encheres ouvertes + mes encheres remportees ");
				logger.info(a);	
			
			}	
	@Test
	void test06_mes_encheres_debutees_ventes_terminees() {
		System.out.println("---------------- debut test achat 6");
		// test mes encheres debutees ventes terminees
				transactionType = "achat";
				requete = 6;
				nomArticle = null;
				noUtilisateurVendeur = 8;
				noUtilisateurAcheteur = 7;
				
				try {
					a = articleVenduDynamiqueDAO.findDynamique(transactionType, requete, nomArticle,noCategorie,noUtilisateurVendeur,noUtilisateurAcheteur );
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    
				System.out.println("test 6 resultat : " + a);
				assertNotNull(a);
				logger.info("Test 6 : mes encheres debutees ventes terminees ");
				logger.info(a);	
			
			}	
	
	@Test
	void test07_mes_ventes_en_cours_mes_encheres_debutees_ventes_terminees() {
		System.out.println("---------------- debut test achat 6");
		// test mes ventes en cours mes encheres debutees ventes terminees
				transactionType = "achat";
				requete = 7;
				nomArticle = null;
				noUtilisateurVendeur = 8;
				noUtilisateurAcheteur = 7;
				
				try {
					a = articleVenduDynamiqueDAO.findDynamique(transactionType, requete, nomArticle,noCategorie,noUtilisateurVendeur,noUtilisateurAcheteur );
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    
				System.out.println("test 7 resultat : " + a);
				assertNotNull(a);
				logger.info("Test 7 : mes ventes en cours mes encheres debutees ventes terminees ");
				logger.info(a);	
			
			}	
}
