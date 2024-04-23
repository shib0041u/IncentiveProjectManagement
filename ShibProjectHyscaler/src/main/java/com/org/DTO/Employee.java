package com.org.DTO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String qualification;
	private String designation;
	private String previous_organization;
	private int previous_salary;
	private int current_salary;
	private String address;
	private String  email;
	private String password;
	private String status;
	private String joinedBy;
	private int sell;
	@ManyToOne
	@JoinColumn
	private Ceo ceo;
	@ManyToOne
	@JoinColumn
	private Incentive incentive;
	@ManyToOne
	@JoinColumn
	private Holiday holiday;
	
	//**************************************************************************************************

	
	public Holiday getHoliday() {
		return holiday;
	}

	public void setHoliday(Holiday holiday) {
		this.holiday = holiday;
	}

	public Employee(String name, String qualification, String designation, String previous_organization,
			int previous_salary, int current_salary, String address, String email, String password, String status,
			int sell,String joinedBy, Ceo ceo) {
		super();
		this.name = name;
		this.qualification = qualification;
		this.designation = designation;
		this.previous_organization = previous_organization;
		this.previous_salary = previous_salary;
		this.current_salary = current_salary;
		this.address = address;
		this.email = email;
		this.password = password;
		this.status = status;
		this.sell= sell;
		this.joinedBy = joinedBy;
		this.ceo = ceo;
	}

	public Employee() {
		super();
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public Incentive getIncentive() {
		return incentive;
	}

	public void setIncentive(Incentive incentive) {
		this.incentive = incentive;
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

	public int getPrevious_salary() {
		return previous_salary;
	}

	public void setPrevious_salary(int previous_salary) {
		this.previous_salary = previous_salary;
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

	public Ceo getCeo() {
		return ceo;
	}

	public void setCeo(Ceo ceo) {
		this.ceo = ceo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	public String getJoinedBy() {
		return joinedBy;
	}

	public void setJoinedBy(String joinedBy) {
		this.joinedBy = joinedBy;
	}

	public int getSell() {
		return sell;
	}

	public void setSell(int sell) {
		this.sell = sell;
	}

	
}
