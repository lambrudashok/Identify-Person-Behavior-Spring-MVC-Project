package com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.model.UserInfoModel;
import com.service.ChangePasswordService;
import com.service.UserRegistrationService;


@Controller
public class SettingsController {

	@Autowired
	ChangePasswordService changeSer;
	
	@Autowired
	UserRegistrationService userSer;
	
	// settings change password page
	@RequestMapping("/changepasswordpage")
	public String getChangePasswordPage() {
		return "changepasswordpage";
	}
	
	
	// change password logic
	@RequestMapping(value="/changepassword", method=RequestMethod.POST)
	public String changePass(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession(false);
		int userID=Integer.parseInt(session.getAttribute("userID").toString());
		String currentPass =request.getParameter("curpass");
		String newPass = request.getParameter("newpass");
	
		int result=changeSer.checkPassword(currentPass, userID);
		if(result>0) {
			int change=changeSer.changeUserPassword(newPass, userID);
			model.addAttribute("msg", "Password changed successfully.");
			return "changepasswordpage";
		}else {
			model.addAttribute("msg", "Current password is incorrect.");
			return "changepasswordpage";
		}
	}
	
	
	// settings delete account page 
	@RequestMapping("/deleteaccountpage")
	public String getDeleteAccountPage(HttpServletRequest request , Model model) {
		HttpSession session =request.getSession(false);
		int userID=Integer.parseInt(session.getAttribute("userID").toString());
		UserInfoModel userInfo=userSer.getUserInfo(userID); // fetch user details
		model.addAttribute("userInfo", userInfo);
		model.addAttribute("userID", userID);
		int checkdelete=userSer.checkRequestDelete(userID); // check request user or not
		model.addAttribute("checkdelete", checkdelete);
		return "deleteaccountpage";
	}
	
	
	// delete account request
	@RequestMapping(value="/deleterequest" ,method=RequestMethod.POST)
	@ResponseBody
	public String requestAccountDelete(HttpServletRequest request) {
		int userID=Integer.parseInt(request.getParameter("userID"));
		int delete=userSer.deleteUserAccount(userID); // delete account request send
		int checkdelete=userSer.checkRequestDelete(userID);
		String str = "";
		if(checkdelete!=0){
			str=str+"<h2>Recover Account ?</h2>";
			str=str+"<div class='btndiv'>";
			str=str+"<button type='submit' id='yesbtn' onclick='recoverRequest("+userID+")' >Yes</button>";
			str=str+"<form name='frm' action='userhomepage' method='post'>";
			str=str+"<button type='submit' id='nobtn' >No</button>";
			str=str+"</form>";
			str=str+"</div>";
		}else{
			str=str+"<h2>Do You Want Delete Account ?</h2>";
			str=str+"<div class='btndiv'>";
			str=str+"<button type='submit' id='yesbtn' onclick='deleteRequest("+userID+")'>Yes</button>";
			str=str+"<form name='frm' action='userhomepage' method='post'>";
			str=str+"<button type='submit' id='nobtn' >No</button>";
			str=str+"</form>";
			str=str+"</div>";	
		}
		return str;
	}
	
	
	
	// recover account request
	@RequestMapping(value="/recoverrequest", method=RequestMethod.POST)
	@ResponseBody
	public String recoverAccountUser(HttpServletRequest request) {
		int userID=Integer.parseInt(request.getParameter("userID"));
		int recover=userSer.recoverAccount(userID); // recover account
		int checkdelete=userSer.checkRequestDelete(userID);
		String str="";
		if(checkdelete!=0){
			str=str+"<h2>Recover Account ?</h2>";
			str=str+"<div class='btndiv'>";
			str=str+"<button type='submit' id='yesbtn' onclick='recoverRequest("+userID+")' >Yes</button>";
			str=str+"<form name='frm' action='userhomepage' method='post'>";
			str=str+"<button type='submit' id='nobtn' >No</button>";
			str=str+"</form>";
			str=str+"</div>";
		}else{
			str=str+"<h2>Do You Want Delete Account ?</h2>";
			str=str+"<div class='btndiv'>";
			str=str+"<button type='submit' id='yesbtn' onclick='deleteRequest("+userID+")'>Yes</button>";
			str=str+"<form name='frm' action='userhomepage' method='post'>";
			str=str+"<button type='submit' id='nobtn' >No</button>";
			str=str+"</form>";
			str=str+"</div>";	
		}
		return str;
	}
	
	
	//settings logout page
	@RequestMapping("/logoutpage")
	public String getLogoutPage(HttpServletRequest request,Model model) {
		HttpSession session = request.getSession(false);
		int userID=Integer.parseInt(session.getAttribute("userID").toString());
		UserInfoModel userInfo = userSer.getUserInfo(userID);
		model.addAttribute("userInfo", userInfo);
		return "logoutpage";
	}
	
	//logout login page
	@RequestMapping(value="/loginpage", method=RequestMethod.POST)
	public String getLoginPage() {
		return "loginpage";
	}
	
	// already register
	@RequestMapping("/loginpage")
	public String getAlredyRegister() {
		return "loginpage";
	}
	
	
}
