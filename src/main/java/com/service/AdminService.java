package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.LoginModel;
import com.model.PostLayoutModel;
import com.model.RegistrationModel;
import com.repository.AdminRepository;

@Service
public class AdminService {

	@Autowired
	AdminRepository adminRepo;
	
	/*check admin login available or not in database*/
	public int checkAdminLogin(LoginModel login) {
		return adminRepo.checkAdminLogin(login);
	}
	
	// fetch all user details
	public List<RegistrationModel> fetchAllUserDetails(){
		return adminRepo.fetchAllUserDetails();
	}
	
	//delete user
	public int deleteUser(int registerid) {
		return adminRepo.deleteUser(registerid);
	}
	
	/*view All posts application users*/
	public List<PostLayoutModel> ViewAllUserPosts(){
		return adminRepo.ViewAllUserPosts();
	}
	
	// fetch delete account  requests  users
	public List<RegistrationModel> fetchDeleteUserAccountReuests(){
		return adminRepo.fetchDeleteUserAccountReuests();
	}
	
	// delete user from delete request
	public int deleteUserRequestAccount(int registerid) {
		return adminRepo.deleteUserRequestAccount(registerid);
	}
	
	// fetch user login details date time 
	public List<LoginModel> viewUserLoginDetails(){
		return adminRepo.viewUserLoginDetails();
	}
		
	
	
	
}
