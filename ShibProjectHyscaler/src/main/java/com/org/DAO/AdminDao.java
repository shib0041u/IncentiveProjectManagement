package com.org.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.org.DTO.Admin;
import com.org.DTO.Admin_Request;
import com.org.DTO.Ceo;
import com.org.dao_Interfaces.AdminDaoInterface;
import com.org.utility.Helper;
@Repository
public class AdminDao implements AdminDaoInterface {


	EntityManagerFactory emf = Helper.getEFactory();
	EntityManager em = emf.createEntityManager();
	@Override
	public Admin fetchAdminByEmail(String email) {
		String jpql= "select a from Admin a where a.email =?1";
		Query query = em.createQuery(jpql);
		query.setParameter(1, email);
		
		List<Admin> list = query.getResultList();
		if (list.isEmpty())
			return null;

		return list.get(0);
	}
	@Override
	public Admin fetchAdminByID(int id) {
		return em.find(Admin.class, id);
	}
	@Override
	public Admin saveAndUpdateAdmin(Admin admin) {
		EntityTransaction et = em.getTransaction();

		et.begin();
		admin =em.merge(admin);
		et.commit();
		return admin;
	}
	@Override
	public List<Admin> fetchAllAdmin() {
		String jpql= "select a from Admin a";
		Query query = em.createQuery(jpql);
		List<Admin> list=   query.getResultList();
		
		if (list.isEmpty())
			return null;
		else
			return list;
	}
	@Override
	public Admin fetchAdminByEmailAndPassword(String email, String password) {
		String jpql= "select a from Admin a where a.email =?1 and a.password=?2";
		Query query = em.createQuery(jpql);
		query.setParameter(1, email);
		query.setParameter(2, password);
		
		List<Admin> list = query.getResultList();
		if (list.isEmpty())
		return null;
		
		return list.get(0);
	}

}
