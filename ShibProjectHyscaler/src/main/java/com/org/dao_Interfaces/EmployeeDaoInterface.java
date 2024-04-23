package com.org.dao_Interfaces;

import java.util.List;

import com.org.DTO.Employee;
import com.org.DTO.Employee_Request;
import com.org.DTO.Holiday;
import com.org.DTO.Incentive;

public interface EmployeeDaoInterface {

	Employee fetchEmployeeByEmail(String email);
	Employee fetchEmployeeByID(int id);
	Employee saveAndUpdateEmployee(Employee employee);
	List<Employee> fetchAllRequests();
	void saveAndUpdateEmployee2(List<Employee> employee, Incentive incentive);
	List<Employee> fetchAllGreaterThanMe(int sale);
	List<Employee> fetchAllGreaterThanMe(int sale,int sale2);
	Employee fetchEmployeeByEmailAndPassword(String email,String password);
	void saveAndUpdateEmployee3(List<Employee> employee, Holiday holiday);
	
}
