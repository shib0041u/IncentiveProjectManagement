package com.org.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.org.DTO.Holiday;
import com.org.DTO.Incentive;
import com.org.dao_Interfaces.HolidayInterface;
import com.org.utility.Helper;
@Repository
public class HolidayDAO implements HolidayInterface {
	EntityManagerFactory emf = Helper.getEFactory();
	EntityManager em = emf.createEntityManager();
	@Override
	public List<Holiday> fetchAll() {
		String jpql= "select a from Holiday a order by a.sale";
		Query query = em.createQuery(jpql);
		List<Holiday> list=   query.getResultList();
		
		if (list.isEmpty())
			return null;
		else
			return list;
	}

	@Override
	public void saveAndUpdateHoliday(Holiday holiday) {
		EntityTransaction et = em.getTransaction();

		et.begin();
		em.merge(holiday);
		et.commit();
	}

	@Override
	public Holiday fetchBySell(int sell) {
		String jpql= "select a from Holiday a where a.sale =?1";
		Query query = em.createQuery(jpql);
		query.setParameter(1, sell);
		
		List<Holiday> list = query.getResultList();
		if (list.isEmpty())
			return null;
		
		return list.get(0);
	}

	@Override
	public Holiday selectSmallAmongThem(int sale) {
		String jpql= "select a from Holiday a where a.sale >?1 order by a.sale";
		Query query = em.createQuery(jpql);
		query.setParameter(1, sale);
		
		List<Holiday> list = query.getResultList();
		if (list.isEmpty())
			return null;
		return list.get(0);
	}

	@Override
	public Holiday selectSmallAmongThem2(int sale) {
		String jpql= "select a from Holiday a where a.sale <=?1 order by a.sale desc";
		Query query = em.createQuery(jpql);
		query.setParameter(1, sale);
		
		List<Holiday> list = query.getResultList();
		if (list.isEmpty())
			return null;
		return list.get(0);
	}

}
