package com.org.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.org.DTO.Admin_Request;
import com.org.dao_Interfaces.AdminRequestDaoInterface;
import com.org.utility.Helper;


@Repository
public class AdminRequestDao implements AdminRequestDaoInterface {
	EntityManagerFactory emf = Helper.getEFactory();
	EntityManager em = emf.createEntityManager();
	@Override
	public Admin_Request fetchAdminRequestByEmail(String email) {
		String jpql= "select a from Admin_Request a where a.email =?1";
		Query query = em.createQuery(jpql);
		query.setParameter(1, email);
		
		List<Admin_Request> list = query.getResultList();
		if (list.isEmpty())
			return null;

		return list.get(0);
	}
	@Override
	public void saveAndUpdateAdminRequest(Admin_Request admin) {
		EntityTransaction et = em.getTransaction();

		et.begin();
		em.merge(admin);
		et.commit();
		
	}
	@Override
	public List<Admin_Request> fetchAllRequest() {
		String jpql= "select a from Admin_Request a";
		Query query = em.createQuery(jpql);
		List<Admin_Request> list=   query.getResultList();
		
		if (list.isEmpty())
			return null;
		else
			return list;
	}
	@Override
	public Admin_Request fetchAdminRequestByID(int id) {
		return em.find(Admin_Request.class, id);
	}
	@Override
	public void removeAdminRequest(Admin_Request admin) {
		// TODO Auto-generated method stub
		EntityTransaction et = em.getTransaction();

		et.begin();
		em.remove(admin);
		et.commit();
		
	}

}
