package com.org.dao_Interfaces;

import java.util.List;

import com.org.DTO.Admin;

public interface AdminDaoInterface {
	
	Admin fetchAdminByEmail(String email);
	Admin fetchAdminByID(int id);
	Admin saveAndUpdateAdmin(Admin admin);
	List<Admin> fetchAllAdmin();
	Admin fetchAdminByEmailAndPassword(String email,String password);
}
