package com.org.DTO;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Admin_Request {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String qualification;
	private String designation;
	private String current_organization;
	private int previous_salary;
	private int expected_salary;
	private String address;
	private String email;
	private String password;
	private String status;
	//*******************************************************************
	
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
	public String getCurrent_organization() {
		return current_organization;
	}
	public void setCurrent_organization(String current_organization) {
		this.current_organization = current_organization;
	}
	public int getPrevious_salary() {
		return previous_salary;
	}
	public void setPrevious_salary(int previous_salary) {
		this.previous_salary = previous_salary;
	}
	public int getExpected_salary() {
		return expected_salary;
	}
	public void setExpected_salary(int expected_salary) {
		this.expected_salary = expected_salary;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
}
