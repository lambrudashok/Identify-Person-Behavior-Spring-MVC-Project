package com.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.NotificationModel;
import com.model.ProfileInformationModel;
import com.model.RegistrationModel;
import com.model.ReportProblemModel;
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
	
	//get registerid of post
	public int getPostRegisterid(int postid) {
		return regRepo.getPostRegisterid(postid);
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
	
	// Notification send user (multiple user)
	public boolean isAddNotification(List<Integer> list, NotificationModel model) {
		return regRepo.isAddNotification(list, model);
	}
	
	// Notification send user (single user)
	public boolean isAddNotification(int sender, NotificationModel model) {
		return regRepo.isAddNotification(sender, model);
	}
	
	// notifications fetch user
	public List<NotificationModel> getAllUserNotification(int registerid){
		return regRepo.getAllUserNotification(registerid);
	}
	
	//count notifications user
	public int getNotificationUserCount(int registerid){
		return regRepo.getNotificationUserCount(registerid);
	}
	
	//when user view notification then automatic notification view
	public int notificationUserViewAutomatic(final int registerid){
		return regRepo.notificationUserViewAutomatic(registerid);
	}
	
	//delete notification user
	public int deleteUserNotification(final int nid){
		return regRepo.deleteUserNotification(nid);
	}
	
	// when user send report problem
	public boolean isAddReportProblemUser(ReportProblemModel model) {
		return regRepo.isAddReportProblemUser(model);
	}
	
}
