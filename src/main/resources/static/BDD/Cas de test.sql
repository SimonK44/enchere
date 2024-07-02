
-- table categorie
INSERT INTO CATEGORIES (libelle) VALUES ('Maison');
INSERT INTO CATEGORIES (libelle) VALUES ('Informatique');
INSERT INTO CATEGORIES (libelle) VALUES ('jardinage');

--table role
INSERT INTO ROLE ( role , is_admin ) VALUES ( 'ROLE_ADMIN' , 1 );
INSERT INTO ROLE ( role , is_admin ) VALUES ( 'ROLE_UTILISATEUR' , 1 );
INSERT INTO ROLE ( role , is_admin ) VALUES ( 'ROLE_UTILISATEUR' , 0 );

--table utilisateur
INSERT INTO UTILISATEURS (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur )
           VALUES ('ENIKim', 'Heejung', 'kim', 'kim.heejung2024@campus-eni.fr', '0605040302', 'rue des peupliers', '44000', 'Seoul', '{bcrypt}$2a$12$dst5jLkKVVsHMp4f3A2AvetYm47irH6U62uYeH2EIC11daSwqc0gS',1000,1) ;
INSERT INTO UTILISATEURS (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur )
           VALUES ('ENIYanis', 'Greco', 'Yanis', 'yanis.greco2024@campus-eni.fr', '0615140302', 'rue des chataigers', '44000', 'Nantes', '{bcrypt}$2a$12$zCRlIMdMJ0n3sd0iuzzjkewp/7G88g74bpq/AZw6Bfs20mAPOEKpu',2000,1) ;
INSERT INTO UTILISATEURS (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur )
           VALUES ('ENILilian', 'Boumendil', 'Lilian', 'lilian.boumendil2024@campus-eni.fr', '0725140322', 'rue du chene', '44000', 'Reze', '{bcrypt}$2a$12$FzN61dWP/EVrAwOI9f2zl.nSqaao5GIsw92CmwSHhv.YpT8oypxJ2',500,1) ;
INSERT INTO UTILISATEURS (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur )
           VALUES ('ENISimon', 'Kervadec', 'Simon', 'simon.kervadec024@campus-eni.fr', '0625140322', 'rue de la foret de broceliande', '29000', 'Brest', '{bcrypt}$2a$12$FRK0EOSH3TWDWO9OHWUo1eM6FGvipbFbt/eTIRz6aBFATYaOWdsJe',500,1) ;


-- table ARTICLE_VENDUS
INSERT INTO ARTICLES_VENDUS (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur_vendeur, no_utilisateur_acheteur, no_categorie)
            VALUES ('Table de salon' , 'une tres belle table en chene', '2024-07-01','2024-07-30', 100, 150, 1, 2, 1); 
INSERT INTO ARTICLES_VENDUS (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur_vendeur, no_utilisateur_acheteur, no_categorie)
            VALUES ('Une pipe' , 'ceci n est pas une pipe', '2024-06-01', '2024-08-15',10, 110, 3,2 ,1); 
INSERT INTO ARTICLES_VENDUS (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, no_utilisateur_vendeur, no_categorie)
            VALUES ('PC de l ENI' , 'PC surpuissant', '2024-08-01', '2024-08-15',10, 3 ,2); 
INSERT INTO ARTICLES_VENDUS (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, no_utilisateur_vendeur, no_categorie)
            VALUES ('Pomme' , 'un excellent fruit plein de vitamines', '2024-03-24', '2024-08-05',10, 3 ,3); 

-- tables retraits
INSERT INTO RETRAITS (no_article, rue, code_postal,ville) VALUES (1, 'rue des rosiers', '44000','Nantes');
INSERT INTO RETRAITS (no_article, rue, code_postal,ville) VALUES (2, 'rue des bouleaux', '44400','reze');
INSERT INTO RETRAITS (no_article, rue, code_postal,ville) VALUES (3, 'rue des frenes', '44400','reze');
INSERT INTO RETRAITS (no_article, rue, code_postal,ville) VALUES (4, 'rue des cerisiers', '56000','Vannes');


-- tables ENCHERES

INSERT INTO ENCHERES (no_utilisateur, no_article, date_enchere, montant_enchere ) VALUES (3,1,'2024-07-01','110');
INSERT INTO ENCHERES (no_utilisateur, no_article, date_enchere, montant_enchere ) VALUES (4,1,'2024-07-01','120');
INSERT INTO ENCHERES (no_utilisateur, no_article, date_enchere, montant_enchere ) VALUES (2,1,'2024-07-02','140');
INSERT INTO ENCHERES (no_utilisateur, no_article, date_enchere, montant_enchere ) VALUES (3,1,'2024-07-02','150');
INSERT INTO ENCHERES (no_utilisateur, no_article, date_enchere, montant_enchere ) VALUES (3,2,'2024-07-02','110');
















