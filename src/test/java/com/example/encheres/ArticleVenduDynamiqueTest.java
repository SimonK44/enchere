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
	
	/// ------------------------------ achat -----------------------------------		
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
	
/// ------------------------------ ventes -----------------------------------	
	@Test
	void test08_mes_ventes_en_cours() {
// test mes ventes en cours
		System.out.println("---------------- debut test ventes 1");
		transactionType = "ventes";
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
	    
		System.out.println("test 8 resultat : " + a);
		assertNotNull(a);
		logger.info("Test 8 : mes ventes en cours");
		logger.info(a);	
	
	}

	@Test
	void test09_ventes_non_debutees() {
		// test encheres en cours
				System.out.println("---------------- debut test ventes 9");
				transactionType = "ventes";
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
			    
				System.out.println("test 9 resultat : " + a);
				assertNotNull(a);
				logger.info("Test 9 : encheres en cours");
				logger.info(a);	
			
			}	
	
	@Test
	void test10_mes_ventes_en_cours_ventes_non_debutees() {
		// test mes ventes en cours ventes non_debutees
		System.out.println("---------------- debut test ventes 10");
				transactionType = "ventes";
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
			    
				System.out.println("test 10 resultat : " + a);
				assertNotNull(a);
				logger.info("Test 10 : mes ventes en cours ventes non_debutees ");
				logger.info(a);	
			
			}	
	
	@Test
	void test11_ventes_terminees() {
		System.out.println("---------------- debut test ventes 11");
		// test ventes terminees
				transactionType = "ventes";
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
			    
				System.out.println("test 11 resultat : " + a);
				assertNotNull(a);
				logger.info("Test 11 : ventes terminees ");
				logger.info(a);	
			
			}	

	@Test
	void test12_mes_ventes_en_cours_ventes_terminees() {
		System.out.println("---------------- debut test ventes 12");
		// test mes ventes en cours ventes terminees
				transactionType = "ventes";
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
			    
				System.out.println("test 12 resultat : " + a);
				assertNotNull(a);
				logger.info("Test 12 : mes ventes en cours ventes terminees ");
				logger.info(a);	
			
			}	
	@Test
	void test13_ventes_non_debutees_ventes_terminees() {
		System.out.println("---------------- debut test ventes 13");
		// test ventes_non_debutees_ventes_terminees
				transactionType = "ventes";
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
			    
				System.out.println("test 13 resultat : " + a);
				assertNotNull(a);
				logger.info("Test 13: ventes_non_debutees_ventes_terminees ");
				logger.info(a);	
			
			}	
	
	@Test
	void test14_mes_ventes_en_cours_ventes_non_debutees_ventes_terminees() {
		System.out.println("---------------- debut test achat 6");
		// test mes ventes en cours mes ventes en cours mes encheres debutees ventes terminees
				transactionType = "ventes";
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
			    
				System.out.println("test 14 resultat : " + a);
				assertNotNull(a);
				logger.info("Test 14 : mes ventes en cours mes ventes en cours mes encheres debutees ventes terminees ");
				logger.info(a);	
			
			}	
	/// ------------------------------ libellé categorie -----------------------------------		
	@Test
	void test15_liblle() {
// test libelle
		System.out.println("---------------- debut test libelle 15");
		transactionType = "achat";
		requete = 1;
		nomArticle = "PC";
		noUtilisateurVendeur = 8;
		noUtilisateurAcheteur = 7;
		
		try {
			a = articleVenduDynamiqueDAO.findDynamique(transactionType, requete, nomArticle,noCategorie,noUtilisateurVendeur,noUtilisateurAcheteur );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		System.out.println("test 15 resultat : " + a);
		assertNotNull(a);
		logger.info("Test 15 : libelle");
		logger.info(a);	
	
	}
	@Test
	void test16_categorie() {
// test categorie
		System.out.println("---------------- debut test categorie 16");
		transactionType = "achat";
		requete = 1;
		nomArticle = null;
		noUtilisateurVendeur = 8;
		noUtilisateurAcheteur = 7;
		noCategorie = 1;
		
		try {
			a = articleVenduDynamiqueDAO.findDynamique(transactionType, requete, nomArticle,noCategorie,noUtilisateurVendeur,noUtilisateurAcheteur );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		System.out.println("test 16 resultat : " + a);
		assertNotNull(a);
		logger.info("Test 16 : libelle");
		logger.info(a);	
	
	}	
	
	@Test
	void test17_Libelle_categorie() {
// test categorie
		System.out.println("---------------- debut test libelle categorie 17");
		transactionType = "achat";
		requete = 1;
		nomArticle = "T";
		noUtilisateurVendeur = 8;
		noUtilisateurAcheteur = 7;
		noCategorie = 1;
		
		try {
			a = articleVenduDynamiqueDAO.findDynamique(transactionType, requete, nomArticle,noCategorie,noUtilisateurVendeur,noUtilisateurAcheteur );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		System.out.println("test 17 resultat : " + a);
		assertNotNull(a);
		logger.info("Test 17 : libelle categorie");
		logger.info(a);	
	
	}	
}
