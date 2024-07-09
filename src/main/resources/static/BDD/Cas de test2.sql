
-- table categorie
--tous : 0
INSERT INTO CATEGORIES (libelle) VALUES ('Informatique'); --1
INSERT INTO CATEGORIES (libelle) VALUES ('Ameublement'); --2 
INSERT INTO CATEGORIES (libelle) VALUES ('Vêtement');--3
INSERT INTO CATEGORIES (libelle) VALUES ('Sport&Loisirs');--4

--table role
INSERT INTO ROLE ( role , is_admin ) VALUES ( 'ROLE_ADMIN' , 1 );
INSERT INTO ROLE ( role , is_admin ) VALUES ( 'ROLE_UTILISATEUR' , 1 );
INSERT INTO ROLE ( role , is_admin ) VALUES ( 'ROLE_UTILISATEUR' , 0 );

--table utilisateur

-- Utilisateurs 1 à 10
INSERT INTO UTILISATEURS (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur)
VALUES
('ENIKim', 'Heejung', 'Kim', 'kim.heejung2024@campus-eni.fr', '0605040302', 'rue des peupliers', '44000', 'Nantes', '$2b$12$/9x8HjA5unV9HgO1GD1ZJe5SDv.RJnFT1v.bL2nZ1mAgEXybsmK0u', 1000, 1), --mots_de_passe : hkim
('ENIYanis', 'Greco', 'Yanis', 'yanis.greco2024@campus-eni.fr', '0615140302', 'rue des chataigniers', '44000', 'Nantes', '$2b$12$kM6PrsRUjRpsaVuo.L0DIue2jWPP7NWSAoH.e4MIq/wiHp8IjFZjS', 2000, 1),--mots_de_passe :ygreco
('ENILilian', 'Boumendil', 'Lilian', 'lilian.boumendil2024@campus-eni.fr', '0725140322', 'rue du chene', '44000', 'Reze', '$2b$12$UlM8Yyg14nKafCV5W00/Pex3SyBX4M1oRQyz.yG4D/7sZh6rg.yLi', 500, 1),--mots_de_passe :lboumendil
('ENISimon', 'Kervadec', 'Simon', 'simon.kervadec024@campus-eni.fr', '0625140322', 'rue de la foret de broceliande', '29000', 'Brest', '$2b$12$GojpsMBEB31FPG5A8X2s5O5Bb8FYMKCq3zHCVDBPRLDjhsT61tNLy', 500, 1),--mots_de_passe :ksimon
('ENIAlice', 'Durand', 'Alice', 'alice.durand2024@campus-eni.fr', '0612345678', 'rue de la paix', '44000', 'Nantes', '$2b$12$./D25Y2BvE/6ugWZWSSfL.zUVFm1sgIcL6ANrBvmkpwoQoJ7Yc/y6', 1500, 0),--mots_de_passe :adurand
--('ENIBob', 'Martin', 'Bob', 'bob.martin2024@campus-eni.fr', '0623456789', 'rue de la liberté', '44600', 'Saint-Nazaire', '$2b$12$.pT8k6Aj3m7JujFbR0Zwhuvp5mDgq2VdO5UNxBISUkObR7JgtLt2y', 1200, 0),--mots_de_passe :bmartin
--('ENIChloe', 'Bernard', 'Chloe', 'chloe.bernard2024@campus-eni.fr', '0634567890', 'rue du moulin', '44800', 'Saint-Herblain', '$2b$12$kHqPddkv.qxK8glH/iWb4Oa/FNrPxE5KzMF2pnN02JvIfTWbGo52i', 800, 0),--mots_de_passe :cbernard
--('ENIDavid', 'Dubois', 'David', 'david.dubois2024@campus-eni.fr', '0645678901', 'rue du parc', '44000', 'Nantes', '$2b$12$WSLRZTSiRNU7/Rkx2G5x1u9lWz9W6cJbrYj0CLfdv7qLziARp/Tzy', 600, 0),--mots_de_passe :ddubois
--('ENIEva', 'Thomas', 'Eva', 'eva.thomas2024@campus-eni.fr', '0656789012', 'rue des fleurs', '44400', 'Reze', '$2b$12$HUE2TwDsUKmH76cV0xPGr.kbDeJviOM7Xj96HyP2CZ2KOyBDqSqq.', 1100, 0),--mots_de_passe :ethomas
--('ENIFrank', 'Robert', 'Frank', 'frank.robert2024@campus-eni.fr', '0667890123', 'rue de l\'église', '44600', 'Saint-Nazaire', '$2b$12$qsqDGKbz/wnfI4iRLwOBR.xp3uL1HJRRvxejeJ.m4rg4LTIZzQAFu', 900, 0);  --mots_de_passe :frobert

--
--
---- Utilisateurs 11 à 20
--INSERT INTO UTILISATEURS (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur)
--VALUES
--('ENIGina', 'Richard', 'Gina', 'gina.richard2024@campus-eni.fr', '0678901234', 'rue du marché', '44800', 'Saint-Herblain', '$2b$12$TflkYvlBb2PVoLXf/FNZyeRmNjKZgf3nYbyAZKuX.AOBzTXbfWxdC', 1300, 0),--mots_de_passe :grichard
--('ENIHugo', 'Petit', 'Hugo', 'hugo.petit2024@campus-eni.fr', '0689012345', 'rue de la mairie', '44000', 'Nantes', '$2b$12$QknYHbZTCKr5IoTC92UJuOJSfDFO6GRubOxsYWhFEyicVlnalv1ri', 1400, 0),--mots_de_passe :hpetit
--('ENIIvy', 'Lemoine', 'Ivy', 'ivy.lemoine2024@campus-eni.fr', '0690123456', 'rue des écoles', '44400', 'Reze', '$2b$12$C1eR1hK7WRuWSeYbLypu7ue9Z6gF1AGvgk15EwlUOdr8ItpfXJ90y', 1250, 0),--mots_de_passe :ilemoine
--('ENIJack', 'Blanc', 'Jack', 'jack.blanc2024@campus-eni.fr', '0610123456', 'rue de la gare', '29000', 'Brest', '$2b$12$AxPtF69zm0mbMKX/NiEwIOAj7OlU6/8cQ1SK1h7BZ2ztcUasCWIM.', 700, 0),--mots_de_passe :jblanc
--('ENIKate', 'Moreau', 'Kate', 'kate.moreau2024@campus-eni.fr', '0620123456', 'rue de l\'université', '44000', 'Nantes', '$2b$12$Ff7MwA6Yq/UfNPLcHG3ODeRzyBSY1y5DpLry5wYmLOHa0n8C9Ag3O', 950, 0),--mots_de_passe :kmoreau
--('ENILeo', 'Fontaine', 'Leo', 'leo.fontaine2024@campus-eni.fr', '0630123456', 'rue du port', '44600', 'Saint-Nazaire', '$2b$12$Ayo45VZ1CJw/HK6NMGSSKuO6E5L/SQG5tUJ4ujO8xS0cJbd97gZCq', 1100, 0),--mots_de_passe :lfontaine
--('ENIMia', 'Lambert', 'Mia', 'mia.lambert2024@campus-eni.fr', '0640123456', 'rue des champs', '44800', 'Saint-Herblain', '$2b$12$hAWIuMvynkcwBxfayKgeieNYNpO5rwblH7u0KhdaNPNq6o3h7HR6S', 1300, 0),--mots_de_passe :mlambert
--('ENINick', 'Giraud', 'Nick', 'nick.giraud2024@campus-eni.fr', '0650123456', 'rue des vignes', '44000', 'Nantes', '$2b$12$kOC58LwsYhTjZ7Cf3A6iSO9boGFU3BB7u7EY2Q4pG5YoAVfsR3Ri.', 800, 0),--mots_de_passe :ngiraud
--('ENIOlga', 'Dupont', 'Olga', 'olga.dupont2024@campus-eni.fr', '0660123456', 'rue du stade', '44400', 'Reze', '$2b$12$DRRP9PL7dVQ/q/Toj5FsXetHp0hEViO8NK1xquBHKM9WIGMDIRmRS', 1200, 0),--mots_de_passe :odupont
--('ENIPaul', 'Roche', 'Paul', 'paul.roche2024@campus-eni.fr', '0670123456', 'rue de l\'horloge', '44600', 'Saint-Nazaire', '$2b$12$4/vJDyXYVwbWJv5G3DP74./tCOVOukOPOj11KNCphUKTlo8OWvRje', 1000, 0);--mots_de_passe :proche
--
--
---- Utilisateurs 21 à 30
--INSERT INTO UTILISATEURS (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur)
--VALUES
--('ENIQuinn', 'Muller', 'Quinn', 'quinn.muller2024@campus-eni.fr', '0680123456', 'rue de la plage', '44800', 'Saint-Herblain', '$2b$12$Ap3r6Hk2YPn/0PE1wx/mCOTQNP.eP0SyduUT9a2c/oT8aqZ.17mF6', 1500, 0),--mots_de_passe :qmuller
--('ENIRose', 'Leroy', 'Rose', 'rose.leroy2024@campus-eni.fr', '0691123456', 'rue du lac', '44000', 'Nantes', '$2b$12$6GoKO/SQNOECQXaQHUmQyO36NCfiKTtkGxJ/uYkYUs0pRf/DdV5py', 1400, 0),--mots_de_passe :rleroy
--('ENISteve', 'Bertrand', 'Steve', 'steve.bertrand2024@campus-eni.fr', '0612123456', 'rue des jardins', '44400', 'Reze', '$2b$12$FS/.4A0NGeTYMlzwAokH..zZIl7q3sSMUmxrV48uU5avtFCnTve82', 1100, 0),--mots_de_passe :sbertrand
--('ENITina', 'Fournier', 'Tina', 'tina.fournier2024@campus-eni.fr', '0623123456', 'rue des lilas', '29000', 'Brest', '$2b$12$myBkeAaK5PmRf9zCgRjFhOKZsFTGi58S91TAli.Sb96OrqI8PB3Pe', 1200, 0),--mots_de_passe :tfournier
--('ENIUgo', 'Dupuis', 'Ugo', 'ugo.dupuis2024@campus-eni.fr', '0634123456', 'rue des pommiers', '44000', 'Nantes', '$2b$12$MdL6z4rTbpzm8JSXta3Z2uh9UoIPw91uZRFjqAkeHkN7xScA8zWeW', 1300, 0),--mots_de_passe :udupuis
--('ENIVera', 'Michel', 'Vera', 'vera.michel2024@campus-eni.fr', '0645123456', 'rue des érables', '44600', 'Saint-Nazaire', '$2b$12$9A2YXXZcFCHEKsArNzCu1.Fm0NPzv.gHs8Z.JyT5esDEbn9JQAl7S', 800, 0),--mots_de_passe :vmichel
--('ENIWalt', 'Benoit', 'Walt', 'walt.benoit2024@campus-eni.fr', '0656123456', 'rue de l\'abbaye', '44800', 'Saint-Herblain', '$2b$12$ox8/6NK9EvF/bfTsT3ZT3e0XPbUFnvvRYJ5bzKOZ5PnT1pj8FVokm', 1000, 0),--mots_de_passe :wbenoit
--('ENIXena', 'Gomez', 'Xena', 'xena.gomez2024@campus-eni.fr', '0667123456', 'rue de la rivière', '44000', 'Nantes', '$2b$12$3lWXAr2R/t4WQ5W4uBd/pesCeF5mdUuXX34KFC7JdXn6Fg4GAMeWu', 1100, 0),--mots_de_passe :xgomez
--('ENIYves', 'Roy', 'Yves', 'yves.roy2024@campus-eni.fr', '0678123456', 'rue des tilleuls', '44400', 'Reze', '$2b$12$Rhf2E03aFzHQgsk4RhAZV.8J3sn6WT8Gss9/bqZoRfBCL9Apt0/Ga', 1200, 0),--mots_de_passe :yroy
--('ENIZoe', 'Henry', 'Zoe', 'zoe.henry2024@campus-eni.fr', '0689123456', 'rue du temple', '44600', 'Saint-Nazaire', '$2b$12$O4xqAgiY6oMFkIjQKskZMe5yabHmq95E5mA0vGhrmrfDujTpbNHFq', 1400, 0);--mots_de_passe :zhenry
--
--
--
---- Utilisateurs 31 à 40
--INSERT INTO UTILISATEURS (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur)
--VALUES
--('ENIAlex', 'Garcia', 'Alex', 'alex.garcia2024@campus-eni.fr', '0692123456', 'rue de la poste', '44800', 'Saint-Herblain', '$2b$12$.1aK0i/60fTtI9htJv7Ks.5ME4Y3vh0k99yD5M2lg.4WrdGbsGSiy', 1500, 0),--mots_de_passe :agarcia
--('ENIBeth', 'Martinez', 'Beth', 'beth.martinez2024@campus-eni.fr', '0613123456', 'rue de la fontaine', '44000', 'Nantes', '$2b$12$ZifXglIzxOd9nuxOeOLB0uZ71XRXOKHhxeFDmoxv9GE2fhS8DZyuy', 1600, 0),--mots_de_passe :bmartinez
--('ENICarl', 'Lefevre', 'Carl', 'carl.lefevre2024@campus-eni.fr', '0624123456', 'rue de la vallée', '44400', 'Reze', '$2b$12$y8s5nl6OD7p.ueYmcmUnZOT.4QzBBbW0ixLplmCbZIDfsT0DLag7W', 1700, 0),--mots_de_passe :clefevre
--('ENIDora', 'Boucher', 'Dora', 'dora.boucher2024@campus-eni.fr', '0635123456', 'rue des près', '29000', 'Brest', '$2b$12$N40eo5vccARHrxRX3NxKTucClqIfH/KKMRk6ZTJcIHnbAh69ZfO6u', 1800, 0),--mots_de_passe :dboucher
--('ENIEvan', 'Dupont', 'Evan', 'evan.dupont2024@campus-eni.fr', '0646123456', 'rue des champs', '44000', 'Nantes', '$2b$12$yYJ.A11d59hEOoQ9P3PSo.9e5AyRlBxR8Zs9yNlaAzETTkqRQHLn2', 1900, 0),--mots_de_passe :edupont
--('ENIFay', 'Masson', 'Fay', 'fay.masson2024@campus-eni.fr', '0657123456', 'rue des roses', '44600', 'Saint-Nazaire', '$2b$12$/.LMgUGg0e9iNkTwsQBCu.8ntzylycMHrRRv.YecyRJhoGSPEi/E6', 2000, 0),--mots_de_passe :fmasson
--('ENIGary', 'Fabre', 'Gary', 'gary.fabre2024@campus-eni.fr', '0668123456', 'rue des acacias', '44800', 'Saint-Herblain', '$2b$12$Y3pmDZPSyEmStswowbi/0OMKdsP8ZLdcjE9.Cg4AlKf/uVb68A7.C', 1500, 0),--mots_de_passe :gfabre
--('ENIHolly', 'Regnier', 'Holly', 'holly.regnier2024@campus-eni.fr', '0679123456', 'rue des pins', '44000', 'Nantes', '$2b$12$kP93yFTGCJCF1BzoWgAVi.Zc3gb5aUtzTeeU75g7oQWFW5PiIoIQi', 1400, 0),--mots_de_passe :hregnier
--('ENIIan', 'Gautier', 'Ian', 'ian.gautier2024@campus-eni.fr', '0680123456', 'rue des saules', '44400', 'Reze', '$2b$12$DZ/8odChSlJfSKGZGHJbneV94Kr1NxvmIY0x9KPf.YjJc9RLM5f5y', 1300, 0),--mots_de_passe :igautier
--('ENIJane', 'Colin', 'Jane', 'jane.colin2024@campus-eni.fr', '0691123456', 'rue des peupliers', '44600', 'Saint-Nazaire', '$2b$12$Mz1v7wxg5OFONx8lR1p4Ee1ZNhbESY7/sorSlBOK.sQAZQjlL58pS', 1200, 0);--mots_de_passe :jnoel
--
---- Utilisateurs 41 à 50
--INSERT INTO UTILISATEURS (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur)
--VALUES
--('ENIKyle', 'Duval', 'Kyle', 'kyle.duval2024@campus-eni.fr', '0614123456', 'rue des chênes', '44800', 'Saint-Herblain', '$2b$12$ESpA5Ed9xG4HUC6Qa/Z4HO/HuW6vhLaMh8a0OfCWGBl6p8qR0UO2W', 1100, 0),--mots_de_passe :kperrot
--('ENILucy', 'Perrin', 'Lucy', 'lucy.perrin2024@campus-eni.fr', '0625123456', 'rue des cerisiers', '44000', 'Nantes', '$2b$12$MLig.RYPwZVJNoTGBuJYIOKczn0NC3d3ZCOQwMzke2BzeIZvQtESq', 1000, 0),--mots_de_passe :lpires
--('ENIMarc', 'Renaud', 'Marc', 'marc.renaud2024@campus-eni.fr', '0636123456', 'rue des noyers', '44400', 'Reze', '$2b$12$/A3A0KJ7.L9Q8yHC0sdRO.6kLFQ4RlBSWm/Nz8HqZkFcjMdtUHsE.', 900, 0),--mots_de_passe :mrenaud
--('ENINina', 'Lecomte', 'Nina', 'nina.lecomte2024@campus-eni.fr', '0647123456', 'rue des oliviers', '29000', 'Brest', '$2b$12$1l4RQjiPhZqUTnGACflLiOd9L00CkRAq4sCdiPyK02cE0P/Dszq8W', 800, 0),--mots_de_passe :nlegrand
--('ENIOscar', 'Marchand', 'Oscar', 'oscar.marchand2024@campus-eni.fr', '0658123456', 'rue des platanes', '44000', 'Nantes', '$2b$12$4F/6gStY5PyKNOgJ5Di8gOEZ2t5wrQYyzgBgnUynEOAcLyR53RTpm', 700, 0),--mots_de_passe :ogilles
--('ENIPia', 'Girard', 'Pia', 'pia.girard2024@campus-eni.fr', '0669123456', 'rue des tilleuls', '44600', 'Saint-Nazaire', '$2b$12$HCN4WEXN4QUk6kMXCqbsWuVpsJmnFXmAUMeFYl5osTjUpwTOvhFnC', 600, 0),--mots_de_passe :pgauthier
--('ENIQin', 'Boyer', 'Qin', 'qin.boyer2024@campus-eni.fr', '0670123456', 'rue des vignes', '44800', 'Saint-Herblain', '$2b$12$y15e4FniT./x3bN9jZCpRO4G7iRzvqz/F1NhZln5YYn01DxAIHfoy', 500, 0),--mots_de_passe :qperrin
--('ENIRay', 'Joly', 'Ray', 'ray.joly2024@campus-eni.fr', '0681123456', 'rue du moulin', '44000', 'Nantes', '$2b$12$ByXoS.qEtybHr5m.cDa7D.Osr8uyU3PRvHym.EFrsQFgblXDYqf5G', 400, 0),--mots_de_passe :rparent
--('ENISue', 'Rossi', 'Sue', 'sue.rossi2024@campus-eni.fr', '0692123456', 'rue du bois', '44400', 'Reze', '$2b$12$XEN54vH0PkvqX45iChT9vOp/JxVSkjTPo03zjM3waPbGeD1/4bnQi', 300, 0),--mots_de_passe :sdufour
--('ENITom', 'Renard', 'Tom', 'tom.renard2024@campus-eni.fr', '0613123456', 'rue du marché', '29000', 'Brest', '$2b$12$Ap3nH2yZyJwSHH/8Z5fTY.gC5aPpsrDbHNG.q.Qjk/2tWy5pP0k0.', 200, 0);--mots_de_passe :thamel
--


-- table ARTICLE_VENDUS
--fin enchère avant 12 juillet



--enchère terminé au 12 juillet
INSERT INTO ARTICLES_VENDUS (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur_vendeur, no_utilisateur_acheteur, no_categorie) VALUES 

('Vélo de montagne', 'Pour pédaler vers l’aventure.', '2024-04-15', '2024-06-01', 150, 175, 3, 2, 4),
('Bureau d\'ordinateur', 'Assez grand pour contenir toutes vos idées.', '2024-05-05', '2024-06-15', 80, 90, 2, 1, 2),
('Sweatshirt drôle', 'Pour les soirées fraîches et les bonnes blagues.', '2024-04-20', '2024-06-05', 25, 30, 2, 4, 3),
('Haltères', 'Pour soulever des poids et des compliments.', '2024-05-10', '2024-06-30', 50, 60, 3, 1, 4),
('Lampe de bureau', 'Eclaire vos idées les plus brillantes.', '2024-05-15', '2024-06-10', 20, 25, 1, 3, 2),
('Montre connectée', 'Elle compte les pas et les moments de joie.', '2024-05-01', '2024-06-25', 100, 120, 1, 4, 1),
('Jeans décontracté', 'Parfait pour un look cool sans effort.', '2024-04-10', '2024-06-01', 30, 35, 2, 1, 3),
('Tente de camping', 'Pour dormir sous les étoiles.', '2024-04-15', '2024-06-05', 100, 110, 3, 2, 4);
('Chaise pliante', 'Parfaite pour les invités indésirables !', '2024-05-10', '2024-06-10', 20, 30, 2, 3, 2),
('T-shirt de super-héros', 'Vous fera sentir invincible, mais n\'empêchera pas les taches de ketchup.', '2024-03-01', '2024-04-01', 15, 20, 3, 4, 3),
--('Raquette de tennis', 'Pour ceux qui aiment frapper des balles et parfois des mouches.', '2024-04-01', '2024-05-01', 50, 70, 4, 5, 4),
--('Laptop vintage', 'Tellement vintage qu\'il fonctionne encore avec MS-DOS.', '2024-02-01', '2024-03-01', 100, 150, 5, 6, 1),
--('Canapé deux places', 'Idéal pour les siestes d\'après-midi et les disputes de couple.', '2024-01-01', '2024-02-01', 200, 250, 6, 7, 2),
--('Pantalon à paillettes', 'Pour briller de mille feux lors de vos sorties disco.', '2024-02-15', '2024-03-15', 30, 45, 7, 8, 3),
--('Balle de football', 'Elle ne fait pas de miracle, mais elle roule bien.', '2024-03-15', '2024-04-15', 25, 35, 8, 9, 4),
--('Imprimante 3D', 'Créez vos propres gadgets inutiles en un clin d\'œil.', '2024-01-20', '2024-02-20', 300, 400, 9, 10, 1),
--('Bibliothèque en kit', 'Un puzzle de 1000 pièces pour les amateurs de défis.', '2024-02-10', '2024-03-10', 80, 100, 10, 11, 2),
--('Chaussettes dépareillées', 'Vous n\'aurez jamais à vous inquiéter de perdre une chaussette.', '2024-01-25', '2024-02-25', 10, 15, 11, 12, 3),
--('Bicyclette', 'Fait travailler les muscles des jambes et la patience.', '2024-03-01', '2024-04-01', 100, 150, 12, 13, 4),
--('Casque de réalité virtuelle', 'Pour vous plonger dans un monde où les bugs sont réels.', '2024-04-01', '2024-05-01', 150, 200, 13, 14, 1),
--('Lit en mezzanine', 'Parfait pour ceux qui aiment l\'aventure à deux mètres du sol.', '2024-05-10', '2024-06-10', 250, 300, 14, 15, 2),
--('Chemise hawaïenne', 'Idéale pour les barbecues et les fêtes kitsch.', '2024-03-20', '2024-04-20', 20, 25, 15, 16, 3),
--('Haltères', 'Pour ceux qui aiment soulever plus que leur propre poids.', '2024-04-15', '2024-05-15', 40, 60, 16, 17, 4),
--('Tablette graphique', 'Pour dessiner des chefs-d\'œuvre ou des gribouillis.', '2024-01-15', '2024-02-15', 120, 160, 17, 18, 1),
--('Chaise longue', 'Parfaite pour s\'allonger et contempler votre jardin... ou vos problèmes.', '2024-05-01', '2024-06-01', 100, 130, 18, 19, 2),
--('Robe à fleurs', 'Pour se sentir comme dans un champ de marguerites.', '2024-02-20', '2024-03-20', 35, 50, 19, 20, 3),
--('Kayak gonflable', 'Pour les aventuriers qui n\'ont pas peur des fuites.', '2024-03-10', '2024-04-10', 150, 200, 20, 21, 4),
--('Chaise ergonomique', 'Parfait pour les réunions interminables!', '2024-06-01', '2024-06-10', 75, 100, 1, 2, 2),
--('Ordinateur portable', 'Un compagnon fidèle pour Netflix.', '2024-05-20', '2024-06-15', 500, 550, 1, 3, 1),
--('T-shirt humoristique', 'Idéal pour les soirées déguisées.', '2024-04-01', '2024-05-30', 10, 15, 2, 3, 3),
--('Raquette de tennis', 'Pour les futurs champions!', '2024-05-10', '2024-06-05', 40, 50, 3, 4, 4),
--('Canapé-lit', 'Pour ceux qui aiment les siestes surprises.', '2024-06-01', '2024-06-12', 200, 250, 2, 1, 2),
--('Smartphone', 'Un téléphone intelligent, sauf quand il est en panne.', '2024-05-01', '2024-06-20', 300, 350, 1, 4, 1),
--('Lampe design', 'Pour illuminer votre intérieur avec classe.', '2024-06-15', '2024-07-10', 45, 2, 2),
--('Montre classique', 'Élégance intemporelle.', '2024-06-01', '2024-07-10', 100, 1, 1),
--('Ordinateur portable gaming', 'Pour dominer les jeux.', '2024-06-01', '2024-07-10', 800, 1, 1),
--('Casquette rigolote', 'Protection solaire avec du style.', '2024-04-10', '2024-05-25', 15, 20, 2, 3, 3),


--données : enchères en cours  12 juillet

('Table de salon' , 'une tres belle table en chene', '2024-07-01','2024-07-30', 100, 150, 1, 2, 2),
('Une pipe' , 'ceci n est pas une pipe', '2024-06-01', '2024-08-15',10, 110, 3,2 ,4),
('PC de l ENI' , 'PC surpuissant', '2024-08-01', '2024-08-15',10, 3 ,1),
('Pomme' , 'un excellent fruit plein de vitamines', '2024-03-24', '2024-08-05',10, 3 ,4),
('Écran 4K', 'Des pixels aussi nets que votre esprit.', '2024-06-01', '2024-07-15', 400, 1, 1),
('Chaise de bureau', 'Pour travailler sans casser le dos.', '2024-06-01', '2024-07-20', 100, 2, 2),
('Robe de soirée', 'Soyez la star de la fête.', '2024-06-05', '2024-07-25', 60, 2, 3),
('Haltères réglables', 'Pour un entraînement sur mesure.', '2024-06-10', '2024-07-30', 70, 3, 4),
('Smartwatch', 'Votre coach de poignet.', '2024-06-20', '2024-07-15', 150, 1, 1),
('Chaussures de running', 'Pour courir après vos rêves.', '2024-06-25', '2024-07-20', 80, 2, 4),
--
--('Fauteuil relax', 'Pour des siestes de qualité.', '2024-06-28', '2024-07-30', 250, 2, 2),
--('Veste en cuir', 'Pour un look rebelle.', '2024-06-05', '2024-07-25', 120, 2, 3),
--('Tapis de yoga', 'Pour des étirements zen.', '2024-06-10', '2024-07-20', 30, 3, 4),
--('Table basse', 'Parfait pour votre café matinal.', '2024-06-15', '2024-07-15', 60, 2, 2),
--('Lunettes de soleil', 'Pour voir la vie en rose.', '2024-06-20', '2024-07-25', 50, 1, 3),
--('Sac à dos', 'Pour les aventures du quotidien.', '2024-06-25', '2024-07-30', 70, 3, 4),
--('Canapé 3 places', 'Confort pour toute la famille.', '2024-06-28', '2024-07-15', 300, 2, 2),
--('Étagère modulable', 'Pour ranger avec style.', '2024-06-05', '2024-07-20', 90, 2, 2),
--('Pantalon cargo', 'Pour un look utilitaire.', '2024-06-10', '2024-07-25', 40, 2, 3),
--('Gants de boxe', 'Pour des coups bien placés.', '2024-06-15', '2024-07-30', 60, 3, 4),
--('Table à manger', 'Convivialité assurée.', '2024-06-20', '2024-07-15', 200, 2, 2),
--('Smartphone dernier cri', 'Pour les technophiles.', '2024-06-25', '2024-07-25', 900, 1, 1),
--('Parka', 'Pour affronter le froid avec style.', '2024-06-28', '2024-07-30', 150, 2, 3),
--('Matelas gonflable', 'Pour dormir partout.', '2024-06-01', '2024-07-15', 70, 3, 4),
--('Bibliothèque', 'Pour les amateurs de lecture.', '2024-06-05', '2024-07-20', 120, 2, 2),
--('Chemise hawaïenne', 'Pour les vacances toute l'année.', '2024-06-10', '2024-07-25', 35, 2, 3),
--('Vélo électrique', 'Pour pédaler sans effort.', '2024-06-15', '2024-07-30', 1200, 3, 4),
--('Table de chevet', 'Pour garder vos secrets de nuit.', '2024-06-20', '2024-07-15', 50, 2, 2),
--('Ordinateur de bureau', 'Pour une productivité maximale.', '2024-06-25', '2024-07-25', 600, 1, 1),
--('Pull en laine', 'Pour rester au chaud.', '2024-06-28', '2024-07-30', 70, 2, 3),
--('Sac de sport', 'Pour transporter vos affaires avec style.', '2024-06-01', '2024-07-15', 40, 3, 4),
--('Tabouret de bar', 'Pour des soirées animées.', '2024-06-05', '2024-07-20', 60, 2, 2),
--('Chapeau élégant', 'Pour un look sophistiqué.', '2024-06-10', '2024-07-25', 30, 2, 3),
--('Ballon de football', 'Pour marquer des buts.', '2024-06-15', '2024-07-30', 20, 3, 4),
--('Armoire', 'Pour ranger vos trésors.', '2024-06-20', '2024-07-15', 300, 2, 2),
--('Camera HD', 'Pour capturer vos meilleurs moments.', '2024-06-25', '2024-07-25', 200, 1, 1),

            
  en chère demarre après 12 juillet

('Robe d\'été', 'Légère et colorée.', '2024-07-14', '2024-07-30', 50, NULL, 2, 3),
('Ballon de basket', 'Pour les dunks spectaculaires.', '2024-07-14', '2024-07-15', 25, NULL, 3, 4),
('Lampadaire design', 'Pour une lumière d\'ambiance.', '2024-07-14', '2024-07-20', 100, NULL, 2, 2),
('Lunettes de vue', 'Pour voir plus clair.', '2024-07-14', '2024-07-25', 80, NULL, 1, 3),
('Tapis de course', 'Pour courir à domicile.', '2024-07-14', '2024-07-30', 600, NULL, 3, 4),
('Table de nuit', 'Pour vos lectures nocturnes.', '2024-07-14', '2024-07-15', 45, NULL, 2, 2),
('Laptop', 'Portable et puissant.', '2024-07-14', '2024-07-25', 700, NULL, 1, 1),
('Veste de pluie', 'Restez au sec.', '2024-07-14', '2024-07-30', 60, NULL, 2, 3),
('Sac de randonnée', 'Pour explorer le monde.', '2024-07-14', '2024-07-15', 100, NULL, 3, 4),
('Chaise pliante', 'Pratique et légère.', '2024-07-14', '2024-07-20', 30, NULL, 2, 2),
('Bague élégante', 'Pour une touche de glamour.', '2024-07-14', '2024-07-25', 150, NULL, 1, 3),
('Tente 4 personnes', 'Pour des aventures en groupe.', '2024-07-14', '2024-07-30', 200, NULL, 3, 4),
('Bureau en bois', 'Classique et robuste.', '2024-07-14', '2024-07-15', 150, NULL, 2, 2),
('Appareil photo numérique', 'Pour capturer chaque instant.', '2024-07-14', '2024-07-25', 300, NULL, 1, 1),
('Blouson en jean', 'Pour un look décontracté.', '2024-07-14', '2024-07-30', 70, NULL, 2, 3);

            
 
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



-- Pour l'objet 1 (Table de salon)
INSERT INTO ENCHERES (no_utilisateur, no_article, date_enchere, montant_enchere)
VALUES
(3, 1, '2024-07-01', 110),
(4, 1, '2024-07-01', 120),
(2, 1, '2024-07-02', 140),
(3, 1, '2024-07-02', 150);

-- Pour l'objet 2 (Une pipe)
INSERT INTO ENCHERES (no_utilisateur, no_article, date_enchere, montant_enchere)
VALUES
(1, 2, '2024-06-15', 20),
(2, 2, '2024-06-20', 30),
(3, 2, '2024-06-25', 40),
(4, 2, '2024-06-30', 50),
(5, 2, '2024-07-01', 60);












