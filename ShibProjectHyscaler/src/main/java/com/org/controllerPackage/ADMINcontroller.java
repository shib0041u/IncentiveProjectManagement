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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.org.DTO.Admin;
import com.org.DTO.Admin_Request;
import com.org.DTO.Ceo;
import com.org.dao_Interfaces.AdminDaoInterface;
import com.org.dao_Interfaces.AdminRequestDaoInterface;
import com.org.dao_Interfaces.CEOdaoInterface;
import com.org.smtp.GenerateOtp;
import com.org.smtp.Smtp;



@Controller
public class ADMINcontroller {
	
	@Autowired
	AdminDaoInterface adminDao;
	
	@Autowired
	AdminRequestDaoInterface requestAdminDao;
	@Autowired
	CEOdaoInterface cx;
	
	@GetMapping("AdminRegisterPage")
	public String AdminRegisterPage() {
		return "admin/register";
	}
	@GetMapping("AdminLogin")
	public String AdminLogin() {
		return "admin/login";
	}
	@PostMapping("viewReqEmployeeAdmin")
	public String viewReqEmployeeAdmin() {
		return "admin/viewReqEmployeeAdmin";
	}
	@GetMapping("viewAllAdminAdmin")
	public String viewAllAdminAdmin() {
		return "admin/viewAllAdminAdmin";
	}
	@GetMapping("viewAllEmployeesAdmin")
	public String viewAllEmployeesAdmin() {
		return "admin/viewAllEmployeesAdmin";
	}
	
	@PostMapping("adminHomeChangePassword")
	public String adminHomeChangePassword(@RequestParam("email") String s,HttpServletRequest request) {
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
		return "admin/changeAdminPassword";
	}
	@PostMapping("changeAdminPasswordHome")
	public String changeAdminPasswordHome(@RequestParam("OTP") String s,@RequestParam("password") String s2,HttpServletRequest request) {
		HttpSession session= request.getSession();
		String otp= (String) session.getAttribute("otp");
		if(otp.equals(s)) {
			Admin admin =(Admin) session.getAttribute("adminObject");
			session.removeAttribute("adminObject");
			admin.setPassword(s2);
			adminDao.saveAndUpdateAdmin(admin);
			session.setAttribute("adminObject", admin);
			session.setAttribute("ok", "Password changed successfully");
		}
		else {
			session.setAttribute("ok", "Request has failed, incorrect OTP");
			
		}
		return "admin/viewMe";
	}
	@PostMapping("UpdateAdminById")
	public String UpdateAdminById(@ModelAttribute Admin admin,HttpServletRequest request) {
		HttpSession session= request.getSession();
		Admin admin2 = adminDao.fetchAdminByID(admin.getId());
		admin2.setName(admin.getName());
		admin2.setQualification(admin.getQualification());
		admin2.setDesignation(admin.getDesignation());
		admin2.setPrevious_organization(admin.getPrevious_organization());
//		admin2.setPrevious_salary(admin.getPrevious_salary());
//		admin2.setCurrent_salary(admin.getCurrent_salary());
		admin2.setAddress(admin.getAddress());
//		admin2.setEmail(admin.getEmail());
		adminDao.saveAndUpdateAdmin(admin2);
		session.setAttribute("ok", "Profile has updated.");
		session.removeAttribute("adminObject");
		session.setAttribute("adminObject", admin2);
		return "admin/viewMe";
	}
	@PostMapping("blockAdminRequest")
	public String blockAdminRequest(HttpServletRequest request) {
		HttpSession session = request.getSession();
		int id =(Integer) session.getAttribute("id");
		session.removeAttribute("id");
		Admin_Request admin = requestAdminDao.fetchAdminRequestByID(id);
		admin.setStatus("Blocked");
		requestAdminDao.saveAndUpdateAdminRequest(admin);
		return "CEO/viewAdminRequest";
	}	
	@PostMapping("activeAdminRequest")
	public String activeAdminRequest(HttpServletRequest request) {
		HttpSession session = request.getSession();
		int id =(Integer) session.getAttribute("id");
		session.removeAttribute("id");
		Admin_Request admin = requestAdminDao.fetchAdminRequestByID(id);
		admin.setStatus("Active");
		requestAdminDao.saveAndUpdateAdminRequest(admin);
		return "CEO/viewAdminRequest";
	}	
	@PostMapping("blockAdmin")
	public String blockAdmin(HttpServletRequest request) {
		HttpSession session = request.getSession();
		int id =(Integer) session.getAttribute("id");
		session.removeAttribute("id");
		Admin admin = adminDao.fetchAdminByID(id);
		admin.setStatus("Blocked");
		adminDao.saveAndUpdateAdmin(admin);
		return "CEO/viewRegisteredAdmins";
	}	
	@PostMapping("activeAdmin")
	public String activeAdmin(HttpServletRequest request) {
		HttpSession session = request.getSession();
		int id =(Integer) session.getAttribute("id");
		session.removeAttribute("id");
		Admin admin = adminDao.fetchAdminByID(id);
		admin.setStatus("Active");
		adminDao.saveAndUpdateAdmin(admin);
		return "CEO/viewRegisteredAdmins";
	}	
	@PostMapping("updateSalaryAdmin")
	public String updateSalaryAdmin(HttpServletRequest request,@RequestParam("expected") int salary) {
		HttpSession session = request.getSession();
		int id =(Integer) session.getAttribute("id");
		session.removeAttribute("id");
		Admin admin = adminDao.fetchAdminByID(id);
		admin.setCurrent_salary(salary);
		adminDao.saveAndUpdateAdmin(admin);
		return "CEO/viewAdminRequest";
	}	
	@PostMapping("registerAsAdmin")
	public String registerAsAdmin(@RequestParam("expected") int salary,HttpServletRequest request) {
		HttpSession session = request.getSession();
		int id =(Integer) session.getAttribute("id");
		Ceo ceo = (Ceo) session.getAttribute("ceoObject");
		session.removeAttribute("id");
		Admin_Request admin = requestAdminDao.fetchAdminRequestByID(id);
		Admin realAdmin = new Admin(
									admin.getName(),
									admin.getQualification(),
									admin.getDesignation(),
									admin.getCurrent_organization(),
									admin.getPrevious_salary(),
									salary,
									admin.getAddress(),
									admin.getEmail(),
									admin.getPassword(),
									"Active",
									ceo);
		realAdmin= adminDao.saveAndUpdateAdmin(realAdmin);
		if(ceo.getAdmin()!=null)
		ceo.getAdmin().add(realAdmin);
		else {
			List<Admin> list=new ArrayList();
			list.add(realAdmin);
			ceo.setAdmin(list);
		}
		cx.saveAndUpdateCEO(ceo);
		requestAdminDao.removeAdminRequest(admin);
		Smtp smtp = new Smtp();
		smtp.sendMail(admin.getEmail(), "Offer Letter", 
					   "Dear "+admin.getName()+",\r\n"
					   		+ "Thanks for showing interest in our organization, "
					   		+ "Congratu;ation!! Your application for Admin has approved.\r\n "
					   		+ "You have to come to our office location with all your documents for verification "
					   		+ "process within 7 days"
					   		+ "It's a pc generated mail. NO need to reply.\r\n"
					   
					   + "Thanks and regards"+"\r\n"
					   + "Syatem/Jarvis");
		return "CEO/viewAdminRequest";
	}	
	@PostMapping("adminRequestRemove")
	public String adminRequestRemove(HttpServletRequest request) {
		HttpSession session = request.getSession();
		int id =(Integer) session.getAttribute("id");
		session.removeAttribute("id");
		Admin_Request admin = requestAdminDao.fetchAdminRequestByID(id);
		
		requestAdminDao.removeAdminRequest(admin);
		Smtp smtp = new Smtp();
		smtp.sendMail(admin.getEmail(), "Application Removed", 
					   "Dear "+admin.getName()+",\r\n"
					   		+ "Thanks for showing interest in our organization, "
					   		+ "but we are sorry to regrate that we can't go forword"
					   		+ "with your application .\r\n"
					   		+ "It's a pc generated mail. NO need to reply.\r\n"
					   
					   + "Thanks and regards"+"\r\n"
					   + "Syatem/Jarvis");
		return "CEO/viewAdminRequest";
	}	
	
	@PostMapping("ADMINregisterRequest")
	public String ADMINregisterRequest(@ModelAttribute Admin_Request admin,HttpServletRequest request) {
		Admin object= adminDao.fetchAdminByEmail(admin.getEmail());
		HttpSession session = request.getSession();
		if(object!=null) {
			if(!object.getStatus().equals("Active")) {
				session.setAttribute("message", "You profile has blocked.");		
			}
			else {
				session.setAttribute("message", "You are already an Admin.");				
			}
			return "admin/register";
		}
		Admin_Request object2= requestAdminDao.fetchAdminRequestByEmail(admin.getEmail());
		if(object2!=null) {
			if(!object2.getStatus().equals("Active")) {
				session.setAttribute("message", "You profile has blocked.");		
			}
			else {
				session.setAttribute("message", "You application has already present in out database.We will be get back to you very soon");				
			}
			return "admin/register";
		}
		admin.setStatus("Active");
		requestAdminDao.saveAndUpdateAdminRequest(admin);
		session.setAttribute("message", "Registration Successful. We will be get back to you very soon.");				
		return "admin/register";
	}
	@PostMapping("adminLoginSubmit")
	public String adminLoginSubmit(@RequestParam("email") String email,@RequestParam("password") String password,HttpServletRequest request) {
		Admin admin = adminDao.fetchAdminByEmailAndPassword(email, password);
		HttpSession session= request.getSession();
		String ret;
		if (admin!= null) {
			if(admin.getStatus().equals("Active")) {
				session.setAttribute("adminObject", admin);
				ret="admin/home";
			}
			else
			{
				session.setAttribute("adminLogin", "Login Failed!! You arew no longer Admin, Account has blocked.");
				ret="admin/login";
			}
		}
		else {
			session.setAttribute("adminLogin", "Login Failed!! Invalid Email or Password");
			ret="admin/login";
		}
		
		return ret;
	}
	@GetMapping("adminForgotPasswordPage")
	public String adminForgotPasswordPage() {
		return "admin/forgotPassword";
	}
	@PostMapping("fectchByEmailAdmin")
	public String fectchByEmailAdmin(@RequestParam("email") String email,HttpServletRequest request) {
		HttpSession session = request.getSession();
		Admin admin = adminDao.fetchAdminByEmail(email);
		if(admin!=null) {
			if(admin.getStatus().equals("Active")) 
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
			return "admin/forgotPasswordOtp";
			}
			else {
				session.setAttribute("forgetFailed", "Access Denied.\r\nYour accout has blocked.");
				return "admin/forgotPassword";
			}
		}
		else {
			session.setAttribute("forgetFailed", "Invalid Email");
			return "admin/forgotPassword";
		}
	}
	@PostMapping("verifyAdminOTP")
	public String verifyAdminOTP(@RequestParam("OTP") String s,HttpServletRequest request) {
		HttpSession session = request.getSession();
		String s2= (String) session.getAttribute("otp");
		if(s.equals(s2)) {
			session.setAttribute("pass2", "yes");
			return "admin/newPassword";
		}
		session.setAttribute("forgetFailed", "Incorrect password");
		return "admin/forgotPassword";
	}
	@PostMapping("changePasswordFinalAdmin")
	public String changePasswordFinalAdmin(@RequestParam("password") String s,HttpServletRequest request) {
		HttpSession session = request.getSession();
		String s2= (String) session.getAttribute("email");
		session.removeAttribute("email");
		Admin admin = adminDao.fetchAdminByEmail(s2);
		adminDao.saveAndUpdateAdmin(admin);
		session.setAttribute("forgetFailed", "Password has changed sucessfully.");
		return "admin/forgotPassword";
	}
	@GetMapping("AdminHomePage")
	public String AdminHomePage() {
		return "admin/home";
	}
	@PostMapping("viewMeAdmin")
	public String viewMeAdmin() {
		return "admin/viewMe";
	}
	@PostMapping("adminLogOut")
	public String adminLogOut(HttpServletRequest request) {
		HttpSession session= request.getSession();
		session.removeAttribute("adminObject");
			
		return "admin/logout";
	}
}
