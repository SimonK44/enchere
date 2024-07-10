package com.example.encheres.bll;

import com.example.encheres.dal.*;
import org.springframework.stereotype.Service;

@Service
public class BoutiqueImpl implements BoutiqueService {
	private BoutiqueDAO boutiqueDAO;



	public BoutiqueImpl(
			BoutiqueDAO boutiqueDAO
	) {
		this.boutiqueDAO = boutiqueDAO;
	}
	@Override
	public void update(int noUtilisateur, int credits) {
		boutiqueDAO.update(noUtilisateur, credits);
	}
}
