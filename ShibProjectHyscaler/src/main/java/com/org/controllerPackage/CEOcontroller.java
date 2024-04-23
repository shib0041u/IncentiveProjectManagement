package com.org.controllerPackage;

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
import com.org.dao_Interfaces.AdminDaoInterface;
import com.org.dao_Interfaces.CEOdaoInterface;
import com.org.dao_Interfaces.EmployeeDaoInterface;
import com.org.smtp.GenerateOtp;
import com.org.smtp.Smtp;

@Controller
public class CEOcontroller {

	@Autowired
	CEOdaoInterface ceoDao;
	@Autowired
	AdminDaoInterface adminDaoaa;
	@Autowired
	EmployeeDaoInterface empDaoaa;
	
	@GetMapping("CEORegisterPage")
	public String CEORegisterPage() {
		return "CEO/register";
	}
	@GetMapping("AdminRequestList")
	public String AdminRequestList() {
		return "CEO/viewAdminRequest";
	}
	@GetMapping("EmployeeRequestList")
	public String EmployeeRequestList() {
		return "CEO/viewEmployeeRequest";
	}
	@GetMapping("registeredAdminList")
	public String registeredAdminList() {
		return "CEO/viewRegisteredAdmins";
	}
	@GetMapping("registeredEmployeeList")
	public String registeredEmployeeList() {
		return "CEO/viewRegisteredEmployees";
	}
	@PostMapping("viewMeCeo")
	public String viewMeCeo() {
		return "CEO/viewMe";
	}
	@PostMapping("holidayCheck")
	public String holidayCheck() {
		return "CEO/holidayPage";
	}
	@PostMapping("newTarget")
	public String newTarget() {
		return "CEO/newTarget";
	}
	@PostMapping("newHolidayTarget")
	public String newHolidayTarget() {
		return "CEO/newHolidayTarget";
	}
	@PostMapping("incentiveCheck")
	public String incentiveCheck() {
		return "CEO/incentivePage";
	}
	@GetMapping("CEOHomePage")
	public String CEOHomePage() {
		return "CEO/home";
	}
	@GetMapping("CeoForgotPasswordPage")
	public String CeoForgotPasswordPage() {
		return "CEO/forgotPassword";
	}
	
	@PostMapping("changePasswordFinalCeo")
	public String changePasswordFinalCeo(@RequestParam("password") String s,HttpServletRequest request) {
		HttpSession session = request.getSession();
		String s2= (String) session.getAttribute("email");
		session.removeAttribute("email");
		Ceo ceo = ceoDao.fetchCeoByEmail(s2);
		ceo.setPassword(s);
		ceoDao.saveAndUpdateCEO(ceo);
		session.setAttribute("forgetFailed", "Password has changed sucessfully.");
		return "CEO/forgotPassword";
	}
	@PostMapping("verifyCeoOTP")
	public String verifyCeoOTP(@RequestParam("OTP") String s,HttpServletRequest request) {
		HttpSession session = request.getSession();
		String s2= (String) session.getAttribute("otp");
		if(s.equals(s2)) {
			session.setAttribute("pass2", "yes");
			return "CEO/newPassword";
		}
		session.setAttribute("forgetFailed", "Incorrect password");
		return "CEO/forgotPassword";
	}
	@PostMapping("fectchByEmailCEO")
	public String fectchByEmailCEO(@RequestParam("email") String email,HttpServletRequest request) {
		HttpSession session = request.getSession();
		Ceo ceo = ceoDao.fetchCeoByEmail(email);
		if(ceo!=null) {
			if(ceo.getStatus().equals("Active")) 
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
			return "CEO/forgotPasswordOtp";
			}
			else {
				session.setAttribute("forgetFailed", "Access Denied.\r\nYour accout has blocked.");
				return "CEO/forgotPassword";
			}
		}
		else {
			session.setAttribute("forgetFailed", "Invalid Email");
			return "CEO/forgotPassword";
		}
	}
	@PostMapping("ceoLoginSubmit")
	public String ceoLoginSubmit(@RequestParam("email") String email,@RequestParam("password") String password,HttpServletRequest request) {
		Ceo ceo= ceoDao.fetchCeoByEmailAndPassword(email, password);
		HttpSession session= request.getSession();
		String ret;
		if (ceo!= null) {
			if(ceo.getStatus().equals("Active")) {
				session.setAttribute("ceoObject", ceo);
				ret="CEO/home";
			}
			else
			{
				session.setAttribute("CeoLogin", "Login Failed!! You arew no longer CEO, Account has blocked.");
				ret="CEO/login";
			}
		}
		else {
			session.setAttribute("CeoLogin", "Login Failed!! Invalid Email or Password");
			ret="CEO/login";
		}
		
		return ret;
	}
	@GetMapping("CEOLogin")
	public String adminLogin() {
		return "CEO/login";
	}
	@PostMapping("verifyOtpCeo")
	public String verifyOtpCeo(@RequestParam("OTP") String enteredOTP,HttpServletRequest request) {
		HttpSession session= request.getSession();
		String orginalOTP=(String) session.getAttribute("otp");
		session.removeAttribute("otp");
		Ceo ceo=(Ceo) session.getAttribute("CEO");
		session.removeAttribute("CEO");
		if(orginalOTP.equals(enteredOTP)) {
			ceo.setCurrent_salary(100000);
			ceo.setStatus("Active");
			ceo.setDesignation("CEO");
			Ceo ceo2 = ceoDao.fetchCeoByStatus("Active");
			if(ceo2!=null) {
				
				List<Admin> list= ceo2.getAdmin();
				List<Employee> list2= ceo2.getEmployee();
				ceo.setEmployee(list2);
				ceo.setAdmin(list);
				ceo=ceoDao.saveAndUpdateCEO(ceo);
				
				if(list!=null)
					for(Admin a:list) {
						a.setCeo(ceo);
//						adminDaoaa.saveAndUpdateAdmin(a);
					}
				if(list2!=null)
					for(Employee a:list2) {
						a.setCeo(ceo);
//						empDaoaa.saveAndUpdateEmployee(a);
					}
				ceo=ceoDao.saveAndUpdateCEO(ceo);
			}
			else
			ceoDao.saveAndUpdateCEO(ceo);
				

			
			session.setAttribute("message", "Registration Successfull, Go to login page.");
		}
		else {
			session.setAttribute("message", "Registration has Failed.");
			
		}
		return "CEO/register";
	}
	@PostMapping("CEOResendOtpRegistrationPage")
	public String CEOResendOtpRegistrationPage(HttpServletRequest request) {
		HttpSession session= request.getSession();
		session.removeAttribute("otp");
		Ceo ceo=(Ceo) session.getAttribute("CEO");
		Smtp smtp = new Smtp();
		GenerateOtp go = new GenerateOtp();
		String otp= go.getOtp();
		smtp.sendMail("shubhranshucontact@gmail.com", "Request for CEO registration", 
					   "Dear Shubh,\r\n"
					   + "Our System has received a request for CEO Registration.\r\n"
					   + "Name: "+ceo.getName()+"\r\n"
					   + "Qualification: "+ceo.getQualification()+"\r\n"
					   + "Designation: "+ceo.getDesignation()+"\r\n"
					   + "Currently Working at: "+ceo.getPrevious_organization()+"\r\n"
					   + "Address: "+ceo.getAddress()+"\r\n"
					   + "Email: "+ceo.getEmail()+"\r\n\r\n"
					   + "Here is the approval otp: "+otp+"\r\n"+"\r\n"
					   + "Thanks and regards"+"\r\n"
					   + "Syatem/Jarvis");
		session.setAttribute("otp", otp);
		return "CEO/emailVerification";
	}
	@PostMapping("CEOregisterSubmit")
	public String CEOregisterSubmit(@ModelAttribute Ceo ceo,HttpServletRequest request) {
		
		Ceo getPresense=ceoDao.fetchCeoByEmail(ceo.getEmail());
		HttpSession session= request.getSession();
		if(getPresense==null) {
		Smtp smtp = new Smtp();
		GenerateOtp go = new GenerateOtp();
		String otp= go.getOtp();
		smtp.sendMail("shubhranshucontact@gmail.com", "Request for CEO registration", 
					   "Dear Shubh,\r\n"
					   + "Our System has received a request for CEO Registration.\r\n"
					   + "Name: "+ceo.getName()+"\r\n"
					   + "Qualification: "+ceo.getQualification()+"\r\n"
					   + "Designation: "+ceo.getDesignation()+"\r\n"
					   + "Currently Working at: "+ceo.getPrevious_organization()+"\r\n"
					   + "Address: "+ceo.getAddress()+"\r\n"
					   + "Email: "+ceo.getEmail()+"\r\n\r\n"
					   + "Here is the approval otp: "+otp+"\r\n"+"\r\n"
					   		+ "Thanks and regards"+"\r\n"
					   				+ "Syatem/Jarvis");
		session.setAttribute("otp", otp);
		session.setAttribute("CEO", ceo);
		return "CEO/emailVerification";
		}
		else {
			if(!getPresense.getStatus().equals("Active")) {
				
				session.setAttribute("message", "You are blocked.");
				return "CEO/register";
			}
			session.setAttribute("message", "An account with same email is already present.");
			return "CEO/register";
		}
	}
	@PostMapping("UpdateCeoById")
	public String UpdateCeoById(@ModelAttribute Ceo ceo,HttpServletRequest request) {
		HttpSession session= request.getSession();
		Ceo ceo2=ceoDao.fetchCeoByID(ceo.getId());
		ceo2.setName(ceo.getName());
		ceo2.setQualification(ceo.getQualification());
		ceo2.setPrevious_organization(ceo.getPrevious_organization());
		ceo2.setAddress(ceo.getAddress());
		
		ceoDao.saveAndUpdateCEO(ceo2);
		session.setAttribute("ok", "Profile has updated.");
		session.removeAttribute("ceoObject");
		session.setAttribute("ceoObject", ceo2);
		return "CEO/viewMe";
	}
	@PostMapping("ceoHomeChangePassword")
	public String ceoHomeChangePassword(@RequestParam("email") String s,HttpServletRequest request) {
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
		return "CEO/changeCEOpassword";
	}
	@PostMapping("changeCeoPasswordHome")
	public String changeCeoPasswordHome(@RequestParam("OTP") String s,@RequestParam("password") String s2,HttpServletRequest request) {
		HttpSession session= request.getSession();
		String otp= (String) session.getAttribute("otp");
		if(otp.equals(s)) {
			Ceo ceo =(Ceo) session.getAttribute("ceoObject");
			session.removeAttribute("ceoObject");
			ceo.setPassword(s2);
			ceoDao.saveAndUpdateCEO(ceo);
			session.setAttribute("ceoObject", ceo);
			session.setAttribute("ok", "Password changed successfully");
		}
		else {
			session.setAttribute("ok", "Request has failed, incorrect OTP");
			
		}
		return "CEO/viewMe";
	}
	@PostMapping("ceoLogOut")
	public String ceoLogOut(HttpServletRequest request) {
		HttpSession session= request.getSession();
		session.removeAttribute("ceoObject");
			
		return "CEO/logout";
	}
}
