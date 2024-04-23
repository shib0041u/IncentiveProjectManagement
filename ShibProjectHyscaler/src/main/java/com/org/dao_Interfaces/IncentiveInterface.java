package com.org.dao_Interfaces;

import java.util.List;

import com.org.DTO.Employee;
import com.org.DTO.Incentive;

public interface IncentiveInterface {

	List<Incentive> fetchAll();
	void saveAndUpdateIncentive(Incentive incentive);
	Incentive fetchBySell(int sell);
	Incentive selectSmallAmongThem(int sale);
	Incentive selectSmallAmongThem2(int sale);
}
