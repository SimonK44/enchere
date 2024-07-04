-- ENCHERES_OUVERTES
SELECT nom_article, description, date_debut_encheres , date_fin_encheres, prix_initial, prix_vente, no_utilisateur_vendeur, no_utilisateur_acheteur , no_categorie FROM ARTICLES_VENDUS WHERE date_debut_encheres <= '2024-07-03' AND date_fin_encheres >= '2024-07-03';

-- MES_ENCHERES_EN_COURS
SELECT nom_article, description, date_debut_encheres , date_fin_encheres, prix_initial, prix_vente, no_utilisateur_vendeur, no_utilisateur_acheteur , no_categorie FROM ARTICLES_VENDUS AS A INNER JOIN ENCHERES AS E ON E.no_article = A.no_article WHERE date_debut_encheres <= '2024-07-03' AND date_fin_encheres >= '2024-07-03' AND E.no_utilisateur = 3;

-- MES_ENCHERES_REMPORTEES
SELECT nom_article, description, date_debut_encheres , date_fin_encheres, prix_initial, prix_vente, no_utilisateur_vendeur, no_utilisateur_acheteur , no_categorie FROM ARTICLES_VENDUS WHERE  date_fin_encheres < '2025-07-03' AND no_utilisateur_acheteur = 2 ;

-- MES_VENTES_EN_COURS
SELECT nom_article, description, date_debut_encheres , date_fin_encheres, prix_initial, prix_vente, no_utilisateur_vendeur, no_utilisateur_acheteur , no_categorie FROM ARTICLES_VENDUS WHERE  date_fin_encheres < '2025-07-03' AND no_utilisateur_vendeur = 1 ;

-- VENTES_NON_DEBUTES 
SELECT nom_article, description, date_debut_encheres , date_fin_encheres, prix_initial, prix_vente, no_utilisateur_vendeur, no_utilisateur_acheteur , no_categorie FROM ARTICLES_VENDUS WHERE date_debut_encheres >= '2023-07-03' AND no_utilisateur_vendeur = 1 ;

--VENTES_TERMINEES 
SELECT nom_article, description, date_debut_encheres , date_fin_encheres, prix_initial, prix_vente, no_utilisateur_vendeur, no_utilisateur_acheteur , no_categorie FROM ARTICLES_VENDUS WHERE date_fin_encheres < '2025-07-03' AND no_utilisateur_vendeur = 1 ;


-- ENCHERES MONTANT MAX
SELECT MAX(montant_enchere ) FROM  ENCHERES where no_article = 1;

--COUNT BY UTILISATEUR
SELECT COUNT(*) FROM UTILISATEURS WHERE no_utilisateur = 1;

--COUNT BY PSEUDO EN MODIF
SELECT COUNT(*) FROM UTILISATEURS WHERE pseudo = 'ENIKim' AND no_utilisateur != 2;

-- COUNT BY NOM/PRENOM EN MODIF
SELECT COUNT(*) FROM UTILISATEURS WHERE nom = 'BOUMENDIL' AND prenom = 'LILIAN' AND no_utilisateur != 5;

-- COUNT BY PSEUDO
SELECT COUNT(*) FROM UTILISATEURS WHERE pseudo = 'ENILilian';

-- COUNT BY NOM/PRENOM
SELECT COUNT(*) FROM UTILISATEURS WHERE nom = 'BOUMENDIL' AND prenom = 'LILIAN'

-- FIND BY PSEUDO
SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur FROM UTILISATEURS WHERE pseudo = 'ENILilian';

-- FIND ALL UTILISATEURS
SELECT pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur FROM UTILISATEURS

--READ UTILISATEUR
SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur FROM UTILISATEURS WHERE no_utilisateur = 1;

-- LECTURE RETRAIT
SELECT no_article, rue, code_postal, ville FROM RETRAITS WHERE no_article = 1;