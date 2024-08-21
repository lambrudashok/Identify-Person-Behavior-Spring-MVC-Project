package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SettingsController {

	
	
	// settings change password page
	@RequestMapping("/changepasswordpage")
	public String getChangePasswordPage() {
		return "changepasswordpage";
	}
	
	
	// settings delete account page 
	@RequestMapping("/deleteaccountpage")
	public String getDeleteAccountPage() {
		return "deleteaccountpage";
	}
	
	
	//settings logout page
	@RequestMapping("/loginpage")
	public String getLogoutPage() {
		return "loginpage";
	}
}
