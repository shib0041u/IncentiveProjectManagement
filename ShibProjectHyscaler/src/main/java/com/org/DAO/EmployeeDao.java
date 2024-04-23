package com.org.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.org.DTO.Admin;
import com.org.DTO.Employee;
import com.org.DTO.Employee_Request;
import com.org.DTO.Holiday;
import com.org.DTO.Incentive;
import com.org.dao_Interfaces.EmployeeDaoInterface;
import com.org.utility.Helper;
@Repository
public class EmployeeDao implements EmployeeDaoInterface{
	

	EntityManagerFactory emf = Helper.getEFactory();
	EntityManager em = emf.createEntityManager();

	@Override
	public Employee fetchEmployeeByEmail(String email) {
		String jpql= "select a from Employee a where a.email =?1";
		Query query = em.createQuery(jpql);
		query.setParameter(1, email);
		
		List<Employee> list = query.getResultList();
		if (list.isEmpty())
			return null;

		return list.get(0);
	}
	@Override
	public Employee fetchEmployeeByID(int id) {
		return em.find(Employee.class, id);
	}
	
	@Override
	public Employee saveAndUpdateEmployee(Employee employee) {
		EntityTransaction et = em.getTransaction();

		et.begin();
		employee=em.merge(employee);
		et.commit();
		
		return employee;
	}
	@Override
	public List<Employee> fetchAllRequests() {
		String jpql= "select a from Employee a";
		Query query = em.createQuery(jpql);
		List<Employee> list=   query.getResultList();
		
		if (list.isEmpty())
			return null;
		else
			return list;
	}
	@Override
	public Employee fetchEmployeeByEmailAndPassword(String email, String password) {
		String jpql= "select a from Employee a where a.email =?1 and a.password=?2";
		Query query = em.createQuery(jpql);
		query.setParameter(1, email);
		query.setParameter(2, password);
		
		List<Employee> list = query.getResultList();
		if (list.isEmpty())
		return null;
		
		return list.get(0);
	}
	@Override
	public List<Employee> fetchAllGreaterThanMe(int sale) {
		String jpql= "select a from Employee a where a.sell >=?1";
		Query query = em.createQuery(jpql);
		query.setParameter(1, sale);
		List<Employee> list = query.getResultList();
		if (list.isEmpty())
		return null;
		
		return list;
	}
	@Override
	public List<Employee> fetchAllGreaterThanMe(int sale, int sale2) {
		String jpql= "select a from Employee a where a.sell >=?1 and a.sell<?2";
		Query query = em.createQuery(jpql);
		query.setParameter(1, sale);
		query.setParameter(2, sale2);
		List<Employee> list = query.getResultList();
		if (list.isEmpty())
		return null;
		
		return list;
	}
	@Override
	public void saveAndUpdateEmployee2(List<Employee> employee, Incentive incentive) {
		EntityTransaction et = em.getTransaction();

		et.begin();
		incentive.setEmployee(employee);
		for(Employee emp:employee) {
			emp.setIncentive(incentive);
			em.merge(emp);
		}
		em.merge(incentive);
		et.commit();
	}
	@Override
	public void saveAndUpdateEmployee3(List<Employee> employee, Holiday holiday) {
		EntityTransaction et = em.getTransaction();
		
		et.begin();
		holiday.setEmployee(employee);
		for(Employee emp:employee) {
			emp.setHoliday(holiday);;
			em.merge(emp);
		}
		em.merge(holiday);
		et.commit();
	}
	
}
