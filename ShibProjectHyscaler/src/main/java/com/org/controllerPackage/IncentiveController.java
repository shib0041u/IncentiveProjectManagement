package com.org.controllerPackage;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.org.DTO.Ceo;
import com.org.DTO.Employee;
import com.org.DTO.Incentive;
import com.org.dao_Interfaces.EmployeeDaoInterface;
import com.org.dao_Interfaces.IncentiveInterface;
@Controller
public class IncentiveController {

	@Autowired
	IncentiveInterface incDao;
	
	@Autowired
	EmployeeDaoInterface empDaox;
	
	@PostMapping("targetSave")
	public String targetSave(@ModelAttribute Incentive incentive,HttpServletRequest request) {
		HttpSession session= request.getSession();
		if( session.getAttribute("check")!= null) {
			session.removeAttribute("check");
			if(incentive.getSale()>=50000) {
				incentive.setHolidayEligibility("Yes");
			}else {
				incentive.setHolidayEligibility("No");
			}
			if(incDao.fetchBySell(incentive.getSale())!=null) {
				session.setAttribute("message", "Failed insert.Duplicate Sale Value");
			}else {
				
				incDao.saveAndUpdateIncentive(incentive);
				Incentive incentive2 = incDao.selectSmallAmongThem(incentive.getSale());
				if(incentive2==null ) {
					List<Employee> employee = empDaox.fetchAllGreaterThanMe(incentive.getSale());
					if(employee!=null)
						empDaox.saveAndUpdateEmployee2(employee,incentive);
				}else {
					List<Employee> employee = empDaox.fetchAllGreaterThanMe(incentive.getSale(), incentive2.getSale());
					if(employee!=null)
						empDaox.saveAndUpdateEmployee2(employee,incentive);
					
					
				}
			}
		}
		
		return "CEO/incentivePage";
	}
}
