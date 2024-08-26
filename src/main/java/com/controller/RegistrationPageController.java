package com.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.model.RegistrationModel;
import com.service.UserRegistrationService;

@Controller
public class RegistrationPageController {

	@Autowired
	UserRegistrationService regSer;
	
	@RequestMapping("/registrationpage")
	public String registrationPageController() {  // registration page UI
		return "registrationpage";
	}
	
	
	// check duplicate email
	@RequestMapping(value="/registercheckemail", method=RequestMethod.POST)
	@ResponseBody
	public String checkEmailRegister(HttpServletRequest request) {
		String email=request.getParameter("email");		
		boolean  emailVal=regSer.searchEmail(email);  //check email duplicate
		String str="";
		if(emailVal) {
			str=str+"<div class='msg' id='emailmsg'>Email already taken. Please choose another</div><br>";	 
		}else {
			str=str+"<div class='msg' id='emailmsg'></div><br>";	 
		}
		return str;
	}
	
	
	// check duplicate username
	@RequestMapping(value="/registercheckusername", method=RequestMethod.POST)
	@ResponseBody
	public String checkUsernameRegister(HttpServletRequest request) {
		String username=request.getParameter("username");	
		boolean usernameVal=regSer.searchUsername(username);  // check username duplicate
		String str="";
		if(usernameVal) {
			str=str+"<div class='msg' id='usernamemsg'>Username already taken. Please choose another</div><br>";
		}else {
			str=str+"<div class='msg' id='usernamemsg'></div><br>";	 
		}
		return str;
	}
	
	
	// retype otp when wrong
	@RequestMapping(value="/registercheckotp", method=RequestMethod.POST)
	@ResponseBody
	public String reEnterOtpForgotPass() {
		String str="";
		str=str+"<div id='otpconfirmGrid'>";
		str=str+"<input type='text' id='otpinput'  placeholder='Enter OTP code'><br>";
		str=str+"<button type='submit' name='otpverifybtn' id='otpverifybtn'  >Confirm</button><br>";
		str=str+"</div>";
		str=str+"<div id='msgotp' class='msg'>Invalid OTP. Please enter correct OTP</div>";
		return str;
	}
	
	
	// registration user
	@RequestMapping(value="/registeruser", method=RequestMethod.POST)
	public String registerUser(HttpServletRequest request, Model mod) {
		String name=request.getParameter("name");
		String email=request.getParameter("email");
		String username=request.getParameter("username");
		String password =request.getParameter("password");
		
		RegistrationModel model = new RegistrationModel();
		model.setCustomername(name);
		model.setEmail(email);
		model.setUsername(username);
		model.setPassword(password);
		boolean register=regSer.isAddNewUserRegistration(model);
		mod.addAttribute("msg", "Registration successful.");
		return "registrationpage";
	}
}
