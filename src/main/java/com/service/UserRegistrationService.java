package com.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.ProfileInformationModel;
import com.model.RegistrationModel;
import com.model.UserInfoModel;
import com.repository.UserRegistrationRepository;


@Service
public class UserRegistrationService {

	@Autowired
	UserRegistrationRepository regRepo;
	
	/*new user insert data in registration master*/
	public boolean isAddNewUserRegistration(RegistrationModel register) {
		return regRepo.isAddNewUserRegistration(register);
	}
	
	/*check email duplicate or not */
	public boolean searchEmail(String email) {
		return regRepo.searchEmail(email);
	}
	
	/*check username duplicate or not */
	public boolean searchUsername(String username) {
		return regRepo.searchUsername(username);
	}
	
	
	/*profile information show*/
	public List<ProfileInformationModel> profileInformation(int registerid) {
		return regRepo.profileInformation(registerid);
	}
	
	// fetch username and name particular user
	public UserInfoModel getUserInfo(int registerid){
		return regRepo.getUserInfo(registerid);
	}
	
	//update or add profile image
	public boolean isAddProfilePhoto(RegistrationModel model) {
		return regRepo.isAddProfilePhoto(model);
	}
	
	//update username
	public int isUpdateUsername(String username,int registerid) {
		return regRepo.isUpdateUsername(username, registerid);
	}
	
	//update email
	public int isUpdateEmail(String email,int registerid) {
		return regRepo.isUpdateEmail(email, registerid);
	}
	
	//update customer name
	public int isUpdateCustomerName(String name,int registerid) {
		return regRepo.isUpdateCustomerName(name, registerid);
	}
		
	
	/*search bio in database*/
	public int searchBio(int registerId) {
		return regRepo.searchBio(registerId);
	}
	
	/*Add Bio*/
	public boolean isaddBio(String bio,int registerid) {
		return regRepo.isaddBio(bio, registerid);
	}
	
	//update bio
	public int isUpdateBio(String bio,int bioid) {
		return regRepo.isUpdateBio(bio, bioid);
	}
	
	
	/*check request account delete*/
	public int checkRequestDelete(int registerId) {
		return regRepo.checkRequestDelete(registerId);
	}
	
	/*recover delete requested account*/
	public int recoverAccount(int register) {
		return regRepo.recoverAccount(register);
	}
	
	/*delete account user*/
	public int deleteUserAccount(int registerId) {
		return regRepo.deleteUserAccount(registerId);
	}
	
}
