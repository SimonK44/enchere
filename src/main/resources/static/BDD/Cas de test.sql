
-- table categorie
INSERT INTO CATEGORIES (libelle) VALUES ('Maison');
INSERT INTO CATEGORIES (libelle) VALUES ('Informatique');
INSERT INTO CATEGORIES (libelle) VALUES ('jardinage');

--table role
INSERT INTO ROLE ( role , is_admin ) VALUES ( 'ROLE_ADMIN' , 1 );
INSERT INTO ROLE ( role , is_admin ) VALUES ( 'ROLE_UTILISATEUR' , 1 );
INSERT INTO ROLE ( role , is_admin ) VALUES ( 'ROLE_UTILISATEUR' , 0 );

--table utlisateur
INSERT INTO UTILISATEURS (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur )
           VALUE ('ENIKim', 'Heejung', 'kim' 'kim.heejung2024@campus-eni.fr', '06 























