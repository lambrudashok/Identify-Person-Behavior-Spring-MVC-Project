package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.LoginModel;
import com.repository.UserLoginRepository;

@Service
public class UserLoginService {

	@Autowired
	UserLoginRepository loginRepo;
	
	/*check user login in database */
	public int checkUserLogin(LoginModel login) {
		return loginRepo.checkUserLogin(login);
	}
	
	/* when user login then login data added in database table*/
	public boolean isAddUserLogin(LoginModel login) {
		return loginRepo.isAddUserLogin(login);
	}
}
