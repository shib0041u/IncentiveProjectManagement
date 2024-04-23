package com.org.dao_Interfaces;

import com.org.DTO.Ceo;

public interface CEOdaoInterface {

	Ceo saveAndUpdateCEO(Ceo ceo);
	Ceo fetchCeoByStatus(String status);
	Ceo fetchCeoByEmail(String email);
	Ceo fetchCeoByEmailAndPassword(String email,String password);
	Ceo fetchCeoByID(int id);
}
