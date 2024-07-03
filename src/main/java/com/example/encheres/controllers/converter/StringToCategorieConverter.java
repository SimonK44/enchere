package com.example.encheres.controllers.converter;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import com.example.encheres.bll.CategorieService;
import com.example.encheres.bo.Categorie;
import org.springframework.core.convert.converter.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component
public class StringToCategorieConverter implements Converter<String, Categorie> {
    @Autowired
    private CategorieService categorieService;

    public StringToCategorieConverter() {
    }

    public Categorie convert(String idGenre) {
        int id = 0;

        try {
            id = Integer.parseInt(idGenre);
        } catch (NumberFormatException var4) {
            var4.printStackTrace();
        }

        return (Categorie)this.categorieService.read(id);
    }
}
