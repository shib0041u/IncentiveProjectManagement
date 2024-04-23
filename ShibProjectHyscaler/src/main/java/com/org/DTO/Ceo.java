package com.org.DTO;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Ceo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String qualification;
	private String designation;
	private String previous_organization;
	private int current_salary;
	private String address;
	private String  email;
	private String password;
	private String status;
	
	@OneToMany(mappedBy = "ceo")
	private List<Admin> admin;

	@OneToMany(mappedBy = "ceo",cascade = CascadeType.ALL)
	private List<Employee> employee;
	
	//**************************************************************************************************
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getPrevious_organization() {
		return previous_organization;
	}
	public void setPrevious_organization(String previous_organization) {
		this.previous_organization = previous_organization;
	}
	public int getCurrent_salary() {
		return current_salary;
	}
	public void setCurrent_salary(int current_salary) {
		this.current_salary = current_salary;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<Admin> getAdmin() {
		return admin;
	}
	public void setAdmin(List<Admin> admin) {
		this.admin = admin;
	}

	public List<Employee> getEmployee() {
		return employee;
	}
	public void setEmployee(List<Employee> employee) {
		this.employee = employee;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Ceo [id=" + id + ", name=" + name + ", qualification=" + qualification + ", designation=" + designation
				+ ", previous_organization=" + previous_organization + ", current_salary=" + current_salary
				+ ", address=" + address + ", email=" + email + ", password=" + password + ", status=" + status
				+ ", admin=" + admin + ", employee=" + employee + "]";
	}

}
