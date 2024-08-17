package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.LoginModel;
import com.repository.AdminRepository;

@Service
public class AdminService {

	@Autowired
	AdminRepository adminRepo;
	
	/*check admin login available or not in database*/
	public int checkAdminLogin(LoginModel login) {
		return adminRepo.checkAdminLogin(login);
	}
}
