package com.example.encheres.bll.contexte;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.example.encheres.bo.Utilisateur;
import com.example.encheres.dal.UtilisateurDAO;

@Service
@Primary
public class ContexteServiceImpl implements ContexteService {
	
	UtilisateurDAO utilisateurDAO;
	
	

	public ContexteServiceImpl(UtilisateurDAO utilisateurDAO) {
		this.utilisateurDAO = utilisateurDAO;
	}

	@Override
	public Utilisateur charger(String pseudo) {		
		return this.utilisateurDAO.findByPseudo(pseudo);
	}

}
