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
	

}
