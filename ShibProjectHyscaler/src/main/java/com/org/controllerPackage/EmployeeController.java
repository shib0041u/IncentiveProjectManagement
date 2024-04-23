package com.org.controllerPackage;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.org.DTO.Admin;
import com.org.DTO.Ceo;
import com.org.DTO.Employee;
import com.org.DTO.Employee_Request;
import com.org.DTO.Holiday;
import com.org.DTO.Incentive;
import com.org.dao_Interfaces.CEOdaoInterface;
import com.org.dao_Interfaces.EmployeeDaoInterface;
import com.org.dao_Interfaces.EmployeeDaoRequestInterface;
import com.org.dao_Interfaces.HolidayInterface;
import com.org.dao_Interfaces.IncentiveInterface;
import com.org.smtp.GenerateOtp;
import com.org.smtp.Smtp;

@Controller
public class EmployeeController {
	
	@Autowired
	EmployeeDaoRequestInterface empReqDao;
	
	@Autowired
	EmployeeDaoInterface empDao;
	@Autowired
	IncentiveInterface iDao;
	
	@Autowired
	HolidayInterface hDao;
	
	@Autowired
	CEOdaoInterface cxyz;
	@GetMapping("EmployeeRegisterPage")
	public String EmployeeRegisterPage() {
		return "employee/register";
	}
	@GetMapping("EmployeeHomePage")
	public String EmployeeHomePage() {
		return "employee/home";
	}
	@GetMapping("EmployeeLogin")
	public String EmployeeLogin() {
		return "employee/login";
	}
	@GetMapping("employeeForgotPasswordPage")
	public String employeeForgotPasswordPage() {
		return "employee/forgotPassword";
	}
	@PostMapping("sellProduct")
	public String sellProduct() {
		return "employee/sellProduct";
	}
	@PostMapping("viewMeEmployee")
	public String viewMeEmployee() {
		return "employee/viewMe";
	}
	@PostMapping("employeeHomeChangePassword")
	public String employeeHomeChangePassword(@RequestParam("email") String s,HttpServletRequest request) {
		Smtp smtp = new Smtp();
		GenerateOtp go = new GenerateOtp();
		String otp= go.getOtp();
		smtp.sendMail(s, "Request for password change", 
				   "Dear User,\r\n"
				   + "Our System has received a password change request from your profile.\r\n"
				   + "Here is the approval otp: "+otp+"\r\n"+"\r\n"
				   + "Thanks and regards"+"\r\n"
				   + "Syatem/Jarvis");
		HttpSession session= request.getSession();
		session.setAttribute("otp",otp);
		return "employee/changeEmployeePassword";
	}
	@PostMapping("viewIncentive")
	public String viewIncentive() {
		return "employee/viewIncentive";
	}
	@PostMapping("productSold")
	public String productSold(@RequestParam("product") int count,HttpServletRequest request) {
		HttpSession session= request.getSession();
		Employee employee =(Employee) session.getAttribute("employeeObject");
		int product = employee.getSell();
		product= product+count;
		employee.setSell(product);
		Incentive i = iDao.selectSmallAmongThem2(product);
		Holiday hh=hDao.selectSmallAmongThem2(product);
		{
			if(i==null) {
				if(employee.getIncentive()!=null) {
					Incentive p = employee.getIncentive();
					p.getEmployee().remove(employee);
					iDao.saveAndUpdateIncentive(p);
					employee.setIncentive(null);
				}
				
					empDao.saveAndUpdateEmployee(employee);				
			}
			else {
				if(employee.getIncentive()!=null) {
					Incentive p = employee.getIncentive();
					p.getEmployee().remove(employee);
					iDao.saveAndUpdateIncentive(p);
				}
					employee.setIncentive(i);
					empDao.saveAndUpdateEmployee(employee);
					i.getEmployee().add(employee);
					iDao.saveAndUpdateIncentive(i);
			}
			if(hh==null) {
				if(employee.getHoliday()!=null) {
					Holiday p = employee.getHoliday();
					p.getEmployee().remove(employee);
					hDao.saveAndUpdateHoliday(p);
					employee.setHoliday(null);
				}
			empDao.saveAndUpdateEmployee(employee);
			}else {
			
				if(employee.getHoliday()!=null) {
					Holiday p = employee.getHoliday();
					p.getEmployee().remove(employee);
					hDao.saveAndUpdateHoliday(p);
					
				}
					employee.setHoliday(hh);
//					empDao.saveAndUpdateEmployee(employee);
					hh.getEmployee().add(employee);
					hDao.saveAndUpdateHoliday(hh);
			}
		}
//		empDao.saveAndUpdateEmployee(employee);
		session.setAttribute("employeeObject", employee);
		return "employee/sellProduct";
	}
	@PostMapping("resetProduct")
	public String resetProduct(HttpServletRequest request) {
		HttpSession session= request.getSession();
		Employee employee =(Employee) session.getAttribute("employeeObject");
		if(employee.getIncentive()!=null) {
			Incentive i = employee.getIncentive();
			i.getEmployee().remove(employee);
			iDao.saveAndUpdateIncentive(i);
			employee.setSell(0);
			employee.setIncentive(null);
			if(employee.getHoliday()!=null) {
				Holiday h = employee.getHoliday();
				h.getEmployee().remove(employee);
				hDao.saveAndUpdateHoliday(h);
				employee.setHoliday(null);
			}
		}
		
		empDao.saveAndUpdateEmployee(employee);
		session.setAttribute("employeeObject", employee);
		return "employee/sellProduct";
	}
	@PostMapping("changeEmployeePasswordHome")
	public String changeEmployeePasswordHome(@RequestParam("OTP") String s,@RequestParam("password") String s2,HttpServletRequest request) {
		HttpSession session= request.getSession();
		String otp= (String) session.getAttribute("otp");
		if(otp.equals(s)) {
			Employee employee = (Employee)session.getAttribute("employeeObject");
			session.removeAttribute("employeeObject");
			employee.setPassword(s2);
			empDao.saveAndUpdateEmployee(employee);
			session.setAttribute("employeeObject", employee);
			session.setAttribute("ok", "Password changed successfully");
		}
		else {
			session.setAttribute("ok", "Request has failed, incorrect OTP");
			
		}
		return "employee/viewMe";
	}
	@PostMapping("UpdateEmployeeById")
	public String UpdateEmployeeById(@ModelAttribute Employee employee,HttpServletRequest request) {
		HttpSession session= request.getSession();
		
		Employee emp = empDao.fetchEmployeeByID(employee.getId());
		emp.setName(employee.getName());
		emp.setQualification(employee.getQualification());
		emp.setDesignation(employee.getDesignation());
		emp.setPrevious_organization(employee.getPrevious_organization());
		emp.setAddress(employee.getAddress());
		empDao.saveAndUpdateEmployee(emp);
		session.setAttribute("ok", "Profile has updated.");
		session.removeAttribute("employeeObject");
		session.setAttribute("employeeObject", emp);
		return "employee/viewMe";
	}
	@PostMapping("EMPLOYEEregisterRequest")
	public String EMPLOYEEregisterRequest(@ModelAttribute Employee_Request employee,HttpServletRequest request) {
		Employee object= empDao.fetchEmployeeByEmail(employee.getEmail());
		HttpSession session = request.getSession();
		if(object!=null) {
			if(!object.getStatus().equals("Active")) {
				session.setAttribute("message", "You profile has blocked.");		
			}
			else {
				session.setAttribute("message", "You are already an employee.");				
			}
			return "employee/register";
		}
		Employee_Request object2= empReqDao.fetchEmployeeRequestByEmail(employee.getEmail());
		if(object2!=null) {
			if(!object2.getStatus().equals("Active")) {
				session.setAttribute("message", "You profile has blocked.");		
			}
			else {
				session.setAttribute("message", "You application has already present in out database.We will be get back to you very soon");				
			}
			return "employee/register";
		}
		employee.setStatus("Active");
		empReqDao.saveAndUpdateEmployeeRequest(employee);
		session.setAttribute("message", "Registration Successful. We will be get back to you very soon.");				
		return "employee/register";
	}
	@PostMapping("blockEmployeeRequest")
	public String blockEmployeeRequest(HttpServletRequest request) {
		HttpSession session = request.getSession();
		int id =(Integer) session.getAttribute("id");
		session.removeAttribute("id");
		Employee_Request employee = empReqDao.fetchEmployeeRequestByID(id);
		employee.setStatus("Blocked");
		empReqDao.saveAndUpdateEmployeeRequest(employee);
		return "CEO/viewEmployeeRequest";
	}
	@PostMapping("blockEmployeeRequestAd")
	public String blockEmployeeRequestAd(HttpServletRequest request) {
		HttpSession session = request.getSession();
		int id =(Integer) session.getAttribute("id");
		session.removeAttribute("id");
		Employee_Request employee = empReqDao.fetchEmployeeRequestByID(id);
		employee.setStatus("Blocked");
		empReqDao.saveAndUpdateEmployeeRequest(employee);
		return "CEO/viewEmployeeRequest";
	}
	@PostMapping("activeEmployeeRequest")
	public String activeEmployeeRequest(HttpServletRequest request) {
		HttpSession session = request.getSession();
		int id =(Integer) session.getAttribute("id");
		session.removeAttribute("id");
		Employee_Request employee = empReqDao.fetchEmployeeRequestByID(id);
		employee.setStatus("Active");
		empReqDao.saveAndUpdateEmployeeRequest(employee);
		return "admin/viewReqEmployeeAdmin";
	}
	@PostMapping("activeEmployeeRequestAd")
	public String activeEmployeeRequestAd(HttpServletRequest request) {
		HttpSession session = request.getSession();
		int id =(Integer) session.getAttribute("id");
		session.removeAttribute("id");
		Employee_Request employee = empReqDao.fetchEmployeeRequestByID(id);
		employee.setStatus("Active");
		empReqDao.saveAndUpdateEmployeeRequest(employee);
		return "admin/viewReqEmployeeAdmin";
	}
	@PostMapping("blockEmployee")
	public String blockEmployee(HttpServletRequest request) {
		HttpSession session = request.getSession();
		int id =(Integer) session.getAttribute("id");
		session.removeAttribute("id");
		Employee employee = empDao.fetchEmployeeByID(id);
		employee.setStatus("Blocked");
		empDao.saveAndUpdateEmployee(employee);
		return "CEO/viewRegisteredEmployee";
	}	
	@PostMapping("activeEmployee")
	public String activeEmployee(HttpServletRequest request) {
		HttpSession session = request.getSession();
		int id =(Integer) session.getAttribute("id");
		session.removeAttribute("id");
		Employee employee = empDao.fetchEmployeeByID(id);
		employee.setStatus("Active");
		empDao.saveAndUpdateEmployee(employee);
		return "CEO/viewRegisteredEmployee";
	}	
	@PostMapping("registerAsEmployeeFromAd")
	public String registerAsEmployeeFromAd(@RequestParam("expected") int salary,HttpServletRequest request) {
		HttpSession session = request.getSession();
		int id =(Integer) session.getAttribute("id");
		session.removeAttribute("id");
		Admin ad = (Admin) session.getAttribute("adminObject");
		Employee_Request employee = empReqDao.fetchEmployeeRequestByID(id);
		Employee realEmployee= new Employee(employee.getName(),
				employee.getQualification(),
				employee.getDesignation(),
				employee.getCurrent_organization(),
				employee.getPrevious_salary(),
				salary,
				employee.getAddress(),
				employee.getEmail(),
				employee.getPassword(),
				"Active",
				0,
				"CEO "+ad.getEmail(),
				ad.getCeo());
		realEmployee= empDao.saveAndUpdateEmployee(realEmployee);
		
		if(ad.getCeo().getEmployee()!=null)
			ad.getCeo().getEmployee().add(realEmployee);
			else {
				List<Employee> list=new ArrayList();
				list.add(realEmployee);
				ad.getCeo().setEmployee(list);
			}
			cxyz.saveAndUpdateCEO(ad.getCeo());
		empReqDao.removeEmployeeRequest(employee);
		
		Smtp smtp = new Smtp();
		smtp.sendMail(realEmployee.getEmail(), "Offer Letter", 
				"Dear "+realEmployee.getName()+",\r\n"
						+ "Thanks for showing interest in our organization, "
						+ "Congratu;ation!! Your application for Employee has approved.\r\n "
						+ "You have to come to our office location with all your documents for verification "
						+ "process within 7 days"
						+ "It's a pc generated mail. NO need to reply.\r\n"
						
					   + "Thanks and regards"+"\r\n"
					   + "Syatem/Jarvis");
		return "admin/viewReqEmployeeAdmin";
	}
	@PostMapping("registerAsEmployeeFromCEO")
	public String registerAsEmployee(@RequestParam("expected") int salary,HttpServletRequest request) {
		HttpSession session = request.getSession();
		int id =(Integer) session.getAttribute("id");
		session.removeAttribute("id");
		Ceo ceo = (Ceo) session.getAttribute("ceoObject");
		Employee_Request employee = empReqDao.fetchEmployeeRequestByID(id);
		Employee realEmployee= new Employee(employee.getName(),
											employee.getQualification(),
											employee.getDesignation(),
											employee.getCurrent_organization(),
											employee.getPrevious_salary(),
											salary,
											employee.getAddress(),
											employee.getEmail(),
											employee.getPassword(),
											"Active",
											0,
											"CEO "+ceo.getEmail(),
											ceo);
		realEmployee=empDao.saveAndUpdateEmployee(realEmployee);
		if(ceo.getEmployee()!=null)
			ceo.getEmployee().add(realEmployee);
			else {
				List<Employee> list=new ArrayList();
				list.add(realEmployee);
				ceo.setEmployee(list);
			}
		cxyz.saveAndUpdateCEO(ceo);
		empReqDao.removeEmployeeRequest(employee);
		
		Smtp smtp = new Smtp();
		smtp.sendMail(realEmployee.getEmail(), "Offer Letter", 
					   "Dear "+realEmployee.getName()+",\r\n"
					   		+ "Thanks for showing interest in our organization, "
					   		+ "Congratu;ation!! Your application for Employee has approved.\r\n "
					   		+ "You have to come to our office location with all your documents for verification "
					   		+ "process within 7 days"
					   		+ "It's a pc generated mail. NO need to reply.\r\n"
					   
					   + "Thanks and regards"+"\r\n"
					   + "Syatem/Jarvis");
		return "CEO/viewEmployeeRequest";
	}
	@PostMapping("employeesRequestRemove")
	public String employeeRequestRemove(HttpServletRequest request) {
		HttpSession session = request.getSession();
		int id =(Integer) session.getAttribute("id");
		session.removeAttribute("id");
		Employee_Request employee = empReqDao.fetchEmployeeRequestByID(id);
		
		empReqDao.removeEmployeeRequest(employee);
		Smtp smtp = new Smtp();
		smtp.sendMail(employee.getEmail(), "Application Removed", 
					   "Dear "+employee.getName()+",\r\n"
					   		+ "Thanks for showing interest in our organization, "
					   		+ "but we are sorry to regrate that we can't go forword"
					   		+ "with your application .\r\n"
					   		+ "It's a pc generated mail. NO need to reply.\r\n"
					   
					   + "Thanks and regards"+"\r\n"
					   + "Syatem/Jarvis");
		return "CEO/viewEmployeeRequest";
	}
	@PostMapping("employeesRequestRemovead")
	public String employeesRequestRemovead(HttpServletRequest request) {
		HttpSession session = request.getSession();
		int id =(Integer) session.getAttribute("id");
		session.removeAttribute("id");
		Employee_Request employee = empReqDao.fetchEmployeeRequestByID(id);
		
		empReqDao.removeEmployeeRequest(employee);
		Smtp smtp = new Smtp();
		smtp.sendMail(employee.getEmail(), "Application Removed", 
				"Dear "+employee.getName()+",\r\n"
						+ "Thanks for showing interest in our organization, "
						+ "but we are sorry to regrate that we can't go forword"
						+ "with your application .\r\n"
						+ "It's a pc generated mail. NO need to reply.\r\n"
						
					   + "Thanks and regards"+"\r\n"
					   + "Syatem/Jarvis");
		return "admin/viewReqEmployeeAdmin";
	}
	@PostMapping("employeeLoginSubmit")
	public String employeeLoginSubmit(@RequestParam("email") String email,@RequestParam("password") String password,HttpServletRequest request) {
		Employee employee = empDao.fetchEmployeeByEmailAndPassword(email, password);
		HttpSession session= request.getSession();
		String ret;
		if (employee!= null) {
			if(employee.getStatus().equals("Active")) {
				session.setAttribute("employeeObject", employee);
				ret="employee/home";
			}
			else
			{
				session.setAttribute("employeeLogin", "Login Failed!! You arew no longer Employee, Account has blocked.");
				ret="employee/login";
			}
		}
		else {
			session.setAttribute("employeeLogin", "Login Failed!! Invalid Email or Password");
			ret="employee/login";
		}
		
		return ret;
	}
	@PostMapping("fectchByEmailEmployee")
	public String fectchByEmailEmployee(@RequestParam("email") String email,HttpServletRequest request) {
		HttpSession session = request.getSession();
		Employee employee = empDao.fetchEmployeeByEmail(email);
		if(employee!=null) {
			if(employee.getStatus().equals("Active")) 
			{
			session.setAttribute("email", email);
			GenerateOtp go = new GenerateOtp();
			String otp= go.getOtp();
			Smtp smtp = new Smtp();
			smtp.sendMail(email, "Request for changing password", 
						   "Dear User,\r\n"
						   + "Our System has received a password change request.\r\n"
						   + "Don't share the otp.\r\n"
						   + "Here is the approval otp: "+otp+"\r\n"+"\r\n"
						   + "Thanks and regards"+"\r\n"
						   + "Syatem/Jarvis");
			session.setAttribute("otp", otp);			
			session.setAttribute("pass", "yes");			
			return "employee/forgotPasswordOtp";
			}
			else {
				session.setAttribute("forgetFailed", "Access Denied.\r\nYour accout has blocked.");
				return "employee/forgotPassword";
			}
		}
		else {
			session.setAttribute("forgetFailed", "Invalid Email");
			return "employee/forgotPassword";
		}
	}
	@PostMapping("verifyEmployeeOTP")
	public String verifyEmployeeOTP(@RequestParam("OTP") String s,HttpServletRequest request) {
		HttpSession session = request.getSession();
		String s2= (String) session.getAttribute("otp");
		if(s.equals(s2)) {
			session.setAttribute("pass2", "yes");
			return "employee/newPassword";
		}
		session.setAttribute("forgetFailed", "Incorrect password");
		return "employee/forgotPassword";
	}
	@PostMapping("changePasswordFinalEmployee")
	public String changePasswordFinalEmployee(@RequestParam("password") String s,HttpServletRequest request) {
		HttpSession session = request.getSession();
		String s2= (String) session.getAttribute("email");
		session.removeAttribute("email");
		Employee employee = empDao.fetchEmployeeByEmail(s2);
		empDao.saveAndUpdateEmployee(employee);
		session.setAttribute("forgetFailed", "Password has changed sucessfully.");
		return "employee/forgotPassword";
	}
	@PostMapping("employeeLogOut")
	public String employeeLogOut(HttpServletRequest request) {
		HttpSession session= request.getSession();
		session.removeAttribute("employeeObject");
			
		return "employee/logout";
	}

}