package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.UserInfoModel;
import com.repository.SearchRepository;

@Service
public class SearchService {

	@Autowired
	SearchRepository serRepo;
	
	
	
	/*fetch all user details for searching*/
	public List<UserInfoModel> fetchAllUserDetails(){
		return serRepo.fetchAllUserDetails();
	}
	
	/*fetch all user details for searching using id home page loading*/
	public List<UserInfoModel> fetchAllUserDetails(int registerid){
		return serRepo.fetchAllUserDetails(registerid);
	}
//	
//	/*fetch all user details for searching using name*/
//	public List<UserInfoModel> fetchAllUserDetails(String name, int registerid){
//		return serRepo.fetchAllUserDetails(name,registerid);
//	}
	
	/*fetch all user details for searching using name JSON search profile page used*/
	public List<UserInfoModel> fetchAllUserDetails(String name){
		return serRepo.fetchAllUserDetails(name);
	}
}
