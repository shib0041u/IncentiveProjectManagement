package com.org.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.org.DTO.Employee;
import com.org.DTO.Incentive;
import com.org.dao_Interfaces.IncentiveInterface;
import com.org.utility.Helper;
@Repository
public class IncentiveDao implements IncentiveInterface {


	EntityManagerFactory emf = Helper.getEFactory();
	EntityManager em = emf.createEntityManager();
	
	@Override
	public List<Incentive> fetchAll() {
		String jpql= "select a from Incentive a order by a.sale";
		Query query = em.createQuery(jpql);
		List<Incentive> list=   query.getResultList();
		
		if (list.isEmpty())
			return null;
		else
			return list;
	}

	@Override
	public void saveAndUpdateIncentive(Incentive incentive) {
		EntityTransaction et = em.getTransaction();

		et.begin();
		em.merge(incentive);
		et.commit();
		
	}

	@Override
	public Incentive fetchBySell(int sale) {
		String jpql= "select a from Incentive a where a.sale =?1";
		Query query = em.createQuery(jpql);
		query.setParameter(1, sale);
		
		List<Incentive> list = query.getResultList();
		if (list.isEmpty())
			return null;
		return list.get(0);
	}
	@Override
	public Incentive selectSmallAmongThem(int sale) {
		String jpql= "select a from Incentive a where a.sale >?1 order by a.sale";
		Query query = em.createQuery(jpql);
		query.setParameter(1, sale);
		
		List<Incentive> list = query.getResultList();
		if (list.isEmpty())
			return null;
		return list.get(0);
	}
	@Override
	public Incentive selectSmallAmongThem2(int sale) {
		String jpql= "select a from Incentive a where a.sale <=?1 order by a.sale desc";
		Query query = em.createQuery(jpql);
		query.setParameter(1, sale);
		
		List<Incentive> list = query.getResultList();
		if (list.isEmpty())
			return null;
		return list.get(0);
	}

}
