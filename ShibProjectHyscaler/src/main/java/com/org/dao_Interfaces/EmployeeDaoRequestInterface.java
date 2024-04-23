package com.org.dao_Interfaces;

import java.util.List;

import com.org.DTO.Employee_Request;

public interface EmployeeDaoRequestInterface {
	Employee_Request fetchEmployeeRequestByEmail(String email);
	void saveAndUpdateEmployeeRequest(Employee_Request employee);
	void removeEmployeeRequest(Employee_Request employee);
	List<Employee_Request> fetchAllRequests();
	Employee_Request fetchEmployeeRequestByID(int id);
}
