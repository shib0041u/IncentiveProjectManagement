package com.org.dao_Interfaces;

import java.util.List;

import com.org.DTO.Admin_Request;

public interface AdminRequestDaoInterface {
	Admin_Request fetchAdminRequestByEmail(String email);
	Admin_Request fetchAdminRequestByID(int id);
	void saveAndUpdateAdminRequest(Admin_Request admin);
	List<Admin_Request> fetchAllRequest();
	void removeAdminRequest(Admin_Request admin);
	
}
