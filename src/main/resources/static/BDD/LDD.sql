-- Script de création de la base de données ENCHERES
--   type :      SQL Server 2012

DROP TABLE ENCHERES
DROP TABLE RETRAITS
DROP TABLE ARTICLES_VENDUS
DROP TABLE UTILISATEURS
DROP TABLE CATEGORIES
DROP TABLE ROLE

CREATE TABLE CATEGORIES (
                            no_categorie     INTEGER IDENTITY(1,1) NOT NULL,
                            libelle          VARCHAR(30) NOT NULL,
                            date_suppression DATE NULL
)

ALTER TABLE CATEGORIES ADD constraint categorie_pk PRIMARY KEY (no_categorie);

CREATE TABLE ENCHERES (
                          no_utilisateur   INTEGER NOT NULL,
                          no_article       INTEGER NOT NULL,
                          montant_enchere  INTEGER NOT NULL,
                          date_enchere     datetime NOT NULL
)

ALTER TABLE ENCHERES ADD constraint enchere_pk PRIMARY KEY (no_utilisateur, no_article, montant_enchere)

CREATE TABLE RETRAITS (
                          no_article       INTEGER NOT NULL,
                          rue              VARCHAR(50) NOT NULL,
                          code_postal      VARCHAR(15) NOT NULL,
                          ville            VARCHAR(30) NOT NULL
)

ALTER TABLE RETRAITS ADD constraint retrait_pk PRIMARY KEY (no_article)

CREATE TABLE UTILISATEURS (
                              no_utilisateur   INTEGER IDENTITY(1,1) NOT NULL,
                              pseudo           VARCHAR(30) NOT NULL,
                              nom              VARCHAR(30) NOT NULL,
                              prenom           VARCHAR(30) NOT NULL,
                              email            VARCHAR(50) NOT NULL,
                              telephone        VARCHAR(15),
                              rue              VARCHAR(50) NOT NULL,
                              code_postal      VARCHAR(10) NOT NULL,
                              ville            VARCHAR(30) NOT NULL,
                              mot_de_passe     VARCHAR(68) NOT NULL,
                              credit           INTEGER NOT NULL,
                              administrateur   bit NOT NULL,
                              date_histo       DATE NULL
)

ALTER TABLE UTILISATEURS ADD constraint utilisateur_pk PRIMARY KEY (no_utilisateur)


CREATE TABLE ARTICLES_VENDUS (
                                 no_article                    INTEGER IDENTITY(1,1) NOT NULL,
                                 nom_article                   VARCHAR(30) NOT NULL,
                                 description                   VARCHAR(300) NOT NULL,
                                 date_debut_encheres           DATE NOT NULL,
                                 date_fin_encheres             DATE NOT NULL,
                                 prix_initial                  INTEGER,
                                 prix_vente                    INTEGER,
                                 no_utilisateur_vendeur        INTEGER NOT NULL,
                                 no_utilisateur_acheteur       INTEGER,
                                 no_categorie                  INTEGER NOT NULL,
                                 date_histo                    DATE NULL,
                                 isRetrait                     bit NOT NULL DEFAULT 0
)

CREATE TABLE ROLE (
                      role                          NVARCHAR(50) NOT NULL,
                      is_admin                      bit NOT NULL
)

ALTER TABLE ROLE ADD CONSTRAINT role_pk PRIMARY KEY (role, is_admin)

ALTER TABLE ARTICLES_VENDUS ADD CONSTRAINT articles_vendus_pk PRIMARY KEY (no_article)

ALTER TABLE ARTICLES_VENDUS
    ADD CONSTRAINT vendeur_utilisateur_fk FOREIGN KEY (no_utilisateur_vendeur)
        REFERENCES UTILISATEURS (no_utilisateur)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION

ALTER TABLE ENCHERES
    ADD CONSTRAINT encheres_articles_vendus_fk FOREIGN KEY (no_article)
        REFERENCES ARTICLES_VENDUS (no_article)
        ON DELETE CASCADE
        ON UPDATE NO ACTION

ALTER TABLE RETRAITS
    ADD CONSTRAINT retraits_articles_vendus_fk FOREIGN KEY (no_article)
        REFERENCES ARTICLES_VENDUS (no_article)
        ON DELETE CASCADE
        ON UPDATE NO ACTION

ALTER TABLE ARTICLES_VENDUS
    ADD CONSTRAINT articles_vendus_categories_fk FOREIGN KEY (no_categorie)
        REFERENCES CATEGORIES (no_categorie)
        ON DELETE CASCADE
        ON UPDATE NO ACTION

ALTER TABLE ARTICLES_VENDUS
    ADD CONSTRAINT acheteur_utilisateur_fk FOREIGN KEY (no_utilisateur_acheteur)
        REFERENCES UTILISATEURS (no_utilisateur)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION

ALTER TABLE ENCHERES
    ADD CONSTRAINT encheres_utilisateur_fk FOREIGN KEY (no_utilisateur)
        REFERENCES UTILISATEURS (no_utilisateur)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
