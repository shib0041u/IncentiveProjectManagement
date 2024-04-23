package com.org.DTO;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Incentive {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int sale;
	private int incentivePercentage;
	private int bonus;
	private String holidayEligibility;
	
	@OneToMany(mappedBy = "incentive",cascade = CascadeType.ALL)
	private List<Employee> employee;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSale() {
		return sale;
	}

	public void setSale(int sale) {
		this.sale = sale;
	}

	public int getIncentivePercentage() {
		return incentivePercentage;
	}

	public void setIncentivePercentage(int incentivePercentage) {
		this.incentivePercentage = incentivePercentage;
	}

	public int getBonus() {
		return bonus;
	}

	public void setBonus(int bonus) {
		this.bonus = bonus;
	}

	public String getHolidayEligibility() {
		return holidayEligibility;
	}

	public void setHolidayEligibility(String holidayEligibility) {
		this.holidayEligibility = holidayEligibility;
	}

	public List<Employee> getEmployee() {
		return employee;
	}

	public void setEmployee(List<Employee> employee) {
		this.employee = employee;
	}
	
	
	
}
