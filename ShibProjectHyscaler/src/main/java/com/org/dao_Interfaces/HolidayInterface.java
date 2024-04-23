package com.org.dao_Interfaces;

import java.util.List;

import com.org.DTO.Holiday;


public interface HolidayInterface {
	List<Holiday> fetchAll();
	void saveAndUpdateHoliday(Holiday holiday);
	Holiday fetchBySell(int sell);
	Holiday selectSmallAmongThem(int sale);
	Holiday selectSmallAmongThem2(int sale);
}
