package com.org.controllerPackage;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.org.DTO.Employee;
import com.org.DTO.Holiday;
import com.org.DTO.Incentive;
import com.org.dao_Interfaces.EmployeeDaoInterface;
import com.org.dao_Interfaces.HolidayInterface;

@Controller
public class HolidayController {
	@Autowired
	HolidayInterface holiDao;

	@Autowired
	EmployeeDaoInterface empDaoH;
	
	@PostMapping("targetSaveHoliday")
	public String targetSaveHoliday(@ModelAttribute Holiday holiday,HttpServletRequest request) {
		HttpSession session= request.getSession();
		if( session.getAttribute("check")!= null) {
			session.removeAttribute("check");
			if(holiday.getSale()<50000) {
				session.setAttribute("message", "Sale must be more than or equal to 50k");
			}else {
				if(holiDao.fetchBySell(holiday.getSale())!=null) {
					session.setAttribute("message", "Failed insert.Duplicate Sale Value");
				}else {
					holiDao.saveAndUpdateHoliday(holiday);
					Holiday h = holiDao.selectSmallAmongThem(holiday.getSale());
					if(h==null) {
						List<Employee> employee = empDaoH.fetchAllGreaterThanMe(holiday.getSale());
						if(employee!=null)
							empDaoH.saveAndUpdateEmployee3(employee,holiday);
					}else {
						List<Employee> employee = empDaoH.fetchAllGreaterThanMe(holiday.getSale(), h.getSale());
						if(employee!=null)
							empDaoH.saveAndUpdateEmployee3(employee,holiday);
					}
				}
				
			}
		}
		
		return "CEO/holidayPage";
	}
}
