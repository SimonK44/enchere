package com.example.encheres.controllers.converter;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import com.example.encheres.bll.CategorieService;
import com.example.encheres.bll.UtilisateurService;
import com.example.encheres.bo.Utilisateur;

import org.springframework.core.convert.converter.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component
public class StringToUtilisateurConverter implements Converter<String, Utilisateur> {
    @Autowired
    private UtilisateurService utilisateurService;

    public StringToUtilisateurConverter(UtilisateurService utilisateurService) {
    this.utilisateurService = utilisateurService;
	}

	public Utilisateur convert(String id) {
        int utilisateurId = Integer.parseInt(id);
        return this.utilisateurService.lectureUtilisateur(utilisateurId);
    }
}
