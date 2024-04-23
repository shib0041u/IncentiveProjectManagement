package com.org.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.org.DTO.Admin_Request;
import com.org.DTO.Employee;
import com.org.DTO.Employee_Request;
import com.org.dao_Interfaces.EmployeeDaoRequestInterface;
import com.org.utility.Helper;
@Repository
public class EmployeeRequestDao implements EmployeeDaoRequestInterface{


	EntityManagerFactory emf = Helper.getEFactory();
	EntityManager em = emf.createEntityManager();
	
	@Override
	public Employee_Request fetchEmployeeRequestByEmail(String email) {
		String jpql= "select a from Employee_Request a where a.email =?1";
		Query query = em.createQuery(jpql);
		query.setParameter(1, email);
		
		List<Employee_Request> list = query.getResultList();
		if (list.isEmpty())
			return null;

		return list.get(0);
	}

	@Override
	public void saveAndUpdateEmployeeRequest(Employee_Request employee) {
		EntityTransaction et = em.getTransaction();

		et.begin();
		em.merge(employee);
		et.commit();
		
	}
	@Override
	public void removeEmployeeRequest(Employee_Request employee) {
		EntityTransaction et = em.getTransaction();
		
		et.begin();
		em.remove(employee);
		et.commit();
	}

	@Override
	public List<Employee_Request> fetchAllRequests() {
		String jpql= "select a from Employee_Request a";
		Query query = em.createQuery(jpql);
		List<Employee_Request> list=   query.getResultList();
		
		if (list.isEmpty())
			return null;
		else
			return list;
	}

	@Override
	public Employee_Request fetchEmployeeRequestByID(int id) {
		return em.find(Employee_Request.class, id);
	}

}
