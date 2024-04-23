package com.org.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.org.DTO.Admin;
import com.org.DTO.Ceo;
import com.org.dao_Interfaces.CEOdaoInterface;
import com.org.utility.Helper;

@Repository
public class CEOdao implements CEOdaoInterface {

	EntityManagerFactory emf = Helper.getEFactory();
	EntityManager em = emf.createEntityManager();
	@Override
	public Ceo saveAndUpdateCEO(Ceo ceo) {
		EntityTransaction et = em.getTransaction();

		et.begin();
		ceo =em.merge(ceo);
		et.commit();
		return ceo;
	}



	@Override
	public Ceo fetchCeoByEmailAndPassword(String email, String password) {
		String jpql= "select a from Ceo a where a.email =?1 and a.password=?2";
		Query query = em.createQuery(jpql);
		query.setParameter(1, email);
		query.setParameter(2, password);
		
		List<Ceo> list = query.getResultList();
		if (list.isEmpty())
		return null;
		
		return list.get(0);
	}

	@Override
	public Ceo fetchCeoByEmail(String email) {
		String jpql= "select a from Ceo a where a.email =?1";
		Query query = em.createQuery(jpql);
		query.setParameter(1, email);
		
		List<Ceo> list = query.getResultList();
		if (list.isEmpty())
			return null;

		return list.get(0);
	}

	@Override
	public Ceo fetchCeoByStatus(String status) {
		String jpql= "select a from Ceo a where a.status =?1";
		Query query = em.createQuery(jpql);
		query.setParameter(1, status);
		List<Ceo> list = query.getResultList();
		if(!list.isEmpty()) {
			Ceo ceo =list.get(0);
			ceo.setStatus("Blocked");
			saveAndUpdateCEO(ceo);
			return ceo;
		}
		return null;
	}



	@Override
	public Ceo fetchCeoByID(int id) {
		return em.find(Ceo.class, id);
	}

}
